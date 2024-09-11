package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Enum.ProductStatus;
import com.example.E_commerce.Exception.InvalidCardException;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.InvalidProductException;
import com.example.E_commerce.Exception.NoSufficientProductException;
import com.example.E_commerce.dto.requestDto.OrderedRequestDto;
import com.example.E_commerce.dto.responseDto.ItemResponseDto;
import com.example.E_commerce.dto.responseDto.OrderedResponseDto;
import com.example.E_commerce.model.*;
import com.example.E_commerce.repository.CardRepository;
import com.example.E_commerce.repository.CustomerRepository;
import com.example.E_commerce.repository.OrderedRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.OrderService;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.transeformer.OrderTransFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderedRepository orderedRepository;
    @Override
    public Ordered placeOrder(Customer customer, Card card) throws NoSufficientProductException {

        Cart cart=customer.getCart();
        Ordered order=new Ordered();
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo=generatemMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);
        List<Item> orderedItems=new ArrayList<>();
        for (Item item: cart.getItemList()){
            try {
                productService.decreaseProductQuantity(item);
                orderedItems.add(item);
            }catch(Exception e){
                throw new NoSufficientProductException("product out of stock");
            }
        }
        order.setItemList(orderedItems);
        for (Item item:orderedItems)
            item.setOrdered(order);
        order.setTotalValue(cart.getCartTotal());

        return order;
    }

    @Override
    public OrderedResponseDto placeOrder(OrderedRequestDto orderedRequestDto) throws Exception {
        Customer customer;
        try{
            customer=customerRepository.findById(orderedRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("customer Id is invalid !!!!");
        }
        Product product;
        try{
            product=productRepository.findById(orderedRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new InvalidProductException("product Id is invalid!!!");
        }

        Card card = cardRepository.findByCardNo(orderedRequestDto.getCardNo());
        if (card == null || card.getCvv() != orderedRequestDto.getCvv() || card.getCustomer() != customer) {
            throw new InvalidCardException("your card is invalid");
        }
              Ordered order=new Ordered();
        Item item=Item.builder()
                .requiredQuantity(orderedRequestDto.getRequiredQuantity())
                .product(product)
                .ordered(order)
                .build();
        try {
            productService.decreaseProductQuantity(item);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
        order.setOrderNo(String.valueOf(UUID.randomUUID()));

        String maskedCardNo=generatemMaskedCard(card.getCardNo());
        order.setCardUsed(maskedCardNo);
        order.setCustomer(customer);

order.setTotalValue(item.getRequiredQuantity()*product.getPrice());
order.getItemList().add(item);
customer.getOrderedList().add(order);
item.setOrdered(order);

product.getItemList().add(item);
orderedRepository.save(order);  //it will save order and product both

        Ordered savedOrder=orderedRepository.save(order);
OrderedResponseDto orderedResponseDto= OrderTransFormer.orderToOrderResponseDto(savedOrder);
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
    }


    public String generatemMaskedCard(String CardNo){
        String maskCardNo="";
        for (int i=0;i<CardNo.length()-4;i++)
            maskCardNo+='X';
        maskCardNo+=CardNo.substring(CardNo.length()-4);
        return maskCardNo;
    }
}
