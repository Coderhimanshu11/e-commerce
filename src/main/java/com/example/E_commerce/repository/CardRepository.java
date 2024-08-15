package com.example.E_commerce.repository;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByCardType(CardType cardType);

    @Query(value = "SELECT c FROM Card c WHERE c.cardType=:cardType AND c.expiryDate>:expiryDate",nativeQuery = true)
    List<Card> findAllByCardTypeAndExpiryDateAfter(@Param("cardType") CardType cardType,@Param("expiryDate")Date expiryDate);
//@Query(value = "SELECT c.cardType FROM Card c GROUP BY c.cardType ORDER BY COUNT(c) DESC")
//    List<Card> findMaxCardTypeWithMaxCount();

//    @Query(value = "SELECT COUNT(c) FROM Card c WHERE c.cardType = :cardType")
//    long countByCardType(@Param("cardType") CardType cardType);

//    @Query(value = "SELECT c FROM Card c WHERE c.cardType IN :cardTypes")
//    List<Card> findByCardTypeIn(@Param("cardTypes") List<CardType> cardTypes);

    Card findByCardNo(String cardNo);
}
