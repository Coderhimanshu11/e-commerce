package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Exception.CardNotFoundException;
import com.example.E_commerce.Exception.InvalidCustomerException;
import com.example.E_commerce.Exception.SellerNotFoundException;
import com.example.E_commerce.dto.requestDto.CardRequestDto;
import com.example.E_commerce.dto.responseDto.CardResponseDto;
import com.example.E_commerce.model.Card;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.model.Seller;
import com.example.E_commerce.repository.CardRepository;
import com.example.E_commerce.repository.CustomerRepository;
import com.example.E_commerce.service.CardService;
import com.example.E_commerce.transeformer.CardTransformer;
import com.example.E_commerce.transeformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CardResponseDto> getBySpecificCard(CardType cardType) throws CardNotFoundException {
        List<Card> cards=cardRepository.findByCardType(cardType);
        if (cards.isEmpty()){
            throw new CardNotFoundException("Card with"+cardType+"not found");
        }
        List<CardResponseDto> cardResponseDtos=new ArrayList<>();
for (Card card:cards){
    cardResponseDtos.add(CardTransformer.CardToCardResponseDto(card));
}
return cardResponseDtos;
    }

    @Override
    public List<CardResponseDto> getMasterCardExpiringAfter(Date expiryDate) {
        List<Card> cards=cardRepository.findAllByCardTypeAndExpiryDateAfter(CardType.MASTERCARD,expiryDate);
        List<CardResponseDto> cardResponseDtos=new ArrayList<>();
        for (Card card:cards){
            cardResponseDtos.add(CardTransformer.CardToCardResponseDto(card));

        }
        return cardResponseDtos;
    }

    @Override
    public CardResponseDto DeleteById(int id) throws CardNotFoundException {
        Card card=cardRepository.findById(id).get();
        if(card==null){
            throw new CardNotFoundException("Card with "+id+"not found");
        }
        cardRepository.deleteById(id);

        return CardTransformer.CardToCardResponseDto(card);

    }


}
