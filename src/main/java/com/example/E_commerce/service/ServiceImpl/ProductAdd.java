package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Enum.Category;
import com.example.E_commerce.Exception.InvalidSellerException;
import com.example.E_commerce.dto.requestDto.ProductRequestDto;
import com.example.E_commerce.dto.responseDto.ProductResponseDto;
import com.example.E_commerce.model.Product;
import com.example.E_commerce.model.Seller;
import com.example.E_commerce.repository.ProductRepository;
import com.example.E_commerce.repository.SellerRepository;
import com.example.E_commerce.service.ProductService;
import com.example.E_commerce.transeformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAdd implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller=sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (Exception e){
            throw new InvalidSellerException("seller doesn't exist");
        }
        Product product= ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProductList().add(product);
        sellerRepository.save(seller);

        ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProduct(Category category) {
      List<Product> productList=productRepository.findByCategory(category);
      List<ProductResponseDto> productResponseDtos=new ArrayList<>();
      for(Product product:productList){
          productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
      }
      return productResponseDtos;
    }

}
