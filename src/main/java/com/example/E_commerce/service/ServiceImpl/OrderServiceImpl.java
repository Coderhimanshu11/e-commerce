package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Exception.NoSufficientProductException;
import com.example.E_commerce.model.*;
import com.example.E_commerce.service.OrderService;
import com.example.E_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;
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
    public String generatemMaskedCard(String CardNo){
        String maskCardNo="";
        for (int i=0;i<CardNo.length()-4;i++)
            maskCardNo+='X';
        maskCardNo+=CardNo.substring(CardNo.length()-4);
        return maskCardNo;
    }
}
