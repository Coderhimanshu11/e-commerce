package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;
import com.example.E_commerce.model.Card;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.repository.CardRepository;
import com.example.E_commerce.repository.CustomerRepository;
import com.example.E_commerce.service.CardService;
import com.example.E_commerce.transeformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardAdd implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {
        Customer customer=customerRepository.findByMobNo(cardRequestDto.getMobNo());
        if (customer==null){
            throw new InvalidCustomerException("Sorry customer doesn't exists");
        }
        Card card= CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCardList().add(card);
        customerRepository.save(customer);
        return CardTransformer.CardToCardResponseDto(card);

    }
}
