package com.example.E_commerce.repository;

import com.example.E_commerce.model.Item;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
