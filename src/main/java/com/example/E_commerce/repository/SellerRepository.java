package com.example.E_commerce.repository;

import com.example.E_commerce.model.Seller;
import com.example.E_commerce.transeformer.SellerTransformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Seller findByEmailId(String email);
}
