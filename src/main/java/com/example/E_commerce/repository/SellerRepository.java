package com.example.E_commerce.repository;

import com.example.E_commerce.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String email);
    void deleteByEmailId(String email);
    List<Seller> findByAge(Integer age);
}
