package com.example.E_commerce.repository;

import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
     List<Product> findByCategory(Category category);
     //List<Product> findBySellerAndProductId(int sellerId,int productId);
    // Void deleteProduct(Product product);
     @Query(value = "Select * from product p where p.price >:price and category=:category",nativeQuery = true )
     List<Product> getProductByPriceAndCategory(Integer price,String category);
}
