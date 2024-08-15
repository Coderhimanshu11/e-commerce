package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Exception.InvalidCardException;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.NoSufficientCartException;
import com.example.E_commerce.dto.requestDto.CheckoutCartRequestDto;
import com.example.E_commerce.dto.responseDto.CartResponseDto;
import com.example.E_commerce.dto.responseDto.ItemResponseDto;
import com.example.E_commerce.dto.responseDto.OrderedResponseDto;
import com.example.E_commerce.model.*;
import com.example.E_commerce.repository.*;
import com.example.E_commerce.service.CartService;
import com.example.E_commerce.service.OrderService;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.transeformer.OrderTransFormer;
import jakarta.mail.FetchProfile;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AddCart implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    ProductService productService;

    @Override
    public CartResponseDto saveCart(int customerId, Item item) {
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();
        int newTotal = cart.getCartTotal() + item.getRequiredQuantity() * item.getProduct().getPrice();
        cart.setCartTotal(newTotal);
        cart.getItemList().add(item);

        cart.setNumberOfItem(cart.getItemList().size());
        Cart savedCart = cartRepository.save(cart);

        CartResponseDto cartResponseDto = CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(customer.getName())
                .numberOfItem(cart.getNumberOfItem())
                .build();

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item itemEntity : cart.getItemList()) {
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
            itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity() * itemEntity.getProduct().getPrice());
            itemResponseDto.setProductName(itemEntity.getProduct().getName());
            itemResponseDto.setCustomerName(customer.getName());
            itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

            itemResponseDtoList.add(itemResponseDto);
        }
    cartResponseDto.setItemResponseDtos(itemResponseDtoList);
        return cartResponseDto;
    }

    @Override
    public OrderedResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws Exception {
        Customer customer;
        try {
            customer = customerRepository.findById(checkoutCartRequestDto.getCustomerId()).get();

        } catch (Exception e) {
            throw new InvalidCustomerException("customer id is invalid");
        }
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        if (card == null || card.getCvv() != checkoutCartRequestDto.getCvv() || card.getCustomer() != customer) {
            throw new InvalidCardException("your card is invalid");
        }
        Cart cart = customer.getCart();
        if (cart.getNumberOfItem() == 0) {
            throw new NoSufficientCartException("cart is empty!!!");
        }
        Ordered savedOrder;
        try {
            Ordered order = orderService.placeOrder(customer, card);
            customer.getOrderedList().add(order);

            savedOrder = orderedRepository.save(order);
            resetCart(cart);
          //  customerRepository.save(customer);
            OrderedResponseDto orderedResponseDto = OrderTransFormer.orderToOrderResponseDto(order);

            List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
            for (Item itemEntity : savedOrder.getItemList()) {
                ItemResponseDto itemResponseDto = new ItemResponseDto();
                itemResponseDto.setPriceOfOneItem(itemEntity.getProduct().getPrice());
                itemResponseDto.setTotalPrice(itemEntity.getRequiredQuantity() * itemEntity.getProduct().getPrice());
                itemResponseDto.setProductName(itemEntity.getProduct().getName());
                itemResponseDto.setCustomerName(customer.getName());
                itemResponseDto.setQuantity(itemEntity.getRequiredQuantity());

                itemResponseDtoList.add(itemResponseDto);

            }
            orderedResponseDto.setItems(itemResponseDtoList);
            return orderedResponseDto;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }


    }
    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        cart.setNumberOfItem(0);
        cart.setItemList(new ArrayList<>());

    }
}
