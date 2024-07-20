package com.example.E_commerce.repository;

import com.example.E_commerce.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderedRepository extends JpaRepository<Ordered,Integer> {
}
