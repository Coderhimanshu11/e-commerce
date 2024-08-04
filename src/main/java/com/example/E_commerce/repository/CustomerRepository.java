package com.example.E_commerce.repository;

import com.example.E_commerce.Enum.CardType;
import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.transeformer.CustomerTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByMobNo(String mobNo);
   // @Query(value = "SELECT * FROM Customer c WHERE c.email = :email AND c.mobNo = :mobNo", nativeQuery = true)
    List<Customer> getCustomerByEmailAndMobNo( String email, String mobNo);
     List<Customer> findByAgeGreaterThan(int age);
@Query(value = "SELECT c FROM Customer c JOIN c.cardList card WHERE card.cardType=:cardType")
     List<Customer> findCustomerByCardType(CardType cardType);
     Optional<Customer> findByEmail(String email);
}
