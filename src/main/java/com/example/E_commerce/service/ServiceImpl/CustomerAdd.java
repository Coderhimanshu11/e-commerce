package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.MobNoAlreadyPresentException;
import com.example.E_commerce.dto.requestDto.CustomerRequestDto;
import com.example.E_commerce.dto.responseDto.CustomerResponseDto;
import com.example.E_commerce.model.Cart;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.repository.CustomerRepository;
import com.example.E_commerce.service.CustomerService;
import com.example.E_commerce.transeformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAdd implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobNoAlreadyPresentException {
        if (customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null) {
            throw new MobNoAlreadyPresentException("Sorry customer already exists");
        }
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart= Cart.builder()
                .totalCost(0)
                .numberOfItem(0)
                .customer(customer)
                .build();
        customer.setCart(cart);

        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }

    @Override
    public List<CustomerResponseDto> getAll() {
         List<Customer> customer=customerRepository.findAll();
         List<CustomerResponseDto> customerResponseDtos=new ArrayList<>();
         for (Customer customer1:customer){

             customerResponseDtos.add(CustomerTransformer.CustomerToCustomerResponseDto(customer1));
         }
         return customerResponseDtos;
    }

    @Override
    public List<CustomerResponseDto> getCustomerByEmailAndMobNo(String email, String mobNo) throws InvalidCustomerException {
        List<Customer> customers=customerRepository.getCustomerByEmailAndMobNo(email,mobNo);
        if (email==null && mobNo==null){
            throw new InvalidCustomerException("Customer with"+email+"or"+mobNo+"not found");
        }
        List<CustomerResponseDto> customerResponseDtos=new ArrayList<>();
        for (Customer customer:customers){
            customerResponseDtos.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
        }
        return customerResponseDtos;
    }

    @Override
    public List<CustomerResponseDto> getByAge(int age) throws InvalidCustomerException {
        List<Customer> customers = customerRepository.findByAgeGreaterThan(age);
        if (customers.isEmpty()) {
            throw new InvalidCustomerException("Customer with age " + age + " not found");
        }
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for (Customer customer : customers) {
//            if (customer.getAge() > 25) {
                customerResponseDtos.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
            }
        //}
        return customerResponseDtos;
    }



    @Override
    public List<CustomerResponseDto> getBySpecificCard(CardType cardType) throws InvalidCustomerException {
        List<Customer> customers=customerRepository.findCustomerByCardType(cardType);
        if (customers.isEmpty()){
            throw new InvalidCustomerException("customer with"+cardType+"not found");
        }
        List<CustomerResponseDto> customerResponseDtos=new ArrayList<>();
        for (Customer customer:customers){
            customerResponseDtos.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
        }
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto changeByEmail(String email,CustomerRequestDto customerRequestDto) throws InvalidCustomerException {
        Optional<Customer> optionalCustomer=customerRepository.findByEmail(email);
        if (optionalCustomer==null){
            throw new InvalidCustomerException("Customer with"+email+"not found");
        }
        Customer customer = optionalCustomer.get();
        customer.setName(customerRequestDto.getName());
        customer.setAge(customerRequestDto.getAge());
        customer.setMobNo(customerRequestDto.getMobNo());
        customer.setAddress(customerRequestDto.getAddress());
        customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto deleteByEmailOrMobNo(String email, String mobNo) throws InvalidCustomerException {
        Customer customer=null;
        if (email!=null && !email.isEmpty()){
            customer=customerRepository.findByEmail(email).orElse(null);
        }
        if (customer == null && mobNo != null && !mobNo.isEmpty()) {
            customer = customerRepository.findByMobNo(mobNo);
        }
        if (customer==null){
            throw new InvalidCustomerException("customer with"+email+"with"+mobNo+"not found");
        }
        customerRepository.delete(customer);
        return CustomerTransformer.CustomerToCustomerResponseDto(customer);
    }


}
