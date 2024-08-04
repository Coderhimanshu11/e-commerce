package com.example.E_commerce.repository;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByCardType(CardType cardType);
}
