package com.example.E_commerce.repository;

import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
     List<Product> findByCategory(Category category);
}
