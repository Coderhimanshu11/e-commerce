package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Enum.ProductStatus;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.InvalidProductException;
import com.example.E_commerce.Exception.NoSufficientProductException;
import com.example.E_commerce.dto.requestDto.ItemRequestDto;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.model.Item;
import com.example.E_commerce.model.Product;
import com.example.E_commerce.repository.CustomerRepository;
import com.example.E_commerce.repository.ItemRepository;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.service.ItemService;
import com.example.E_commerce.transeformer.ItemTransFormer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;


    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidCustomerException, InvalidProductException, NoSufficientProductException {
        Customer customer;
        try{
            customer=customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("customer Id is invalid !!!!");
        }
        Product product;
        try{
            product=productRepository.findById(itemRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new InvalidProductException("product Id is invalid!!!");
        }
        if (itemRequestDto.getRequiredQuantity()>product.getQuantity() || product.getProductStatus()!= ProductStatus.AVAILABLE){
          throw new NoSufficientProductException("Out of stock product !!!");
        }
        Item item= ItemTransFormer.ItemRequestDtoToItem(itemRequestDto);
         item.setCart(customer.getCart());
         item.setProduct(product);

         product.getItemList().add(item);
       //   Product Savedproduct=productRepository.save(product);

         return itemRepository.save(item);
    }
}
