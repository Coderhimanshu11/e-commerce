package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Exception.EmailIDAlreadyPresentException;
import com.example.E_commerce.dto.requestDto.SellerRequestDto;
import com.example.E_commerce.dto.responseDto.SellerResponseDto;
import com.example.E_commerce.model.Seller;
import com.example.E_commerce.repository.SellerRepository;
import com.example.E_commerce.service.SellerService;
import com.example.E_commerce.transeformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerAdd implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto sellerAdd(SellerRequestDto sellerRequestDto) throws EmailIDAlreadyPresentException {
        if (sellerRepository.findByEmailId(sellerRequestDto.getEmail())!=null)
            throw new EmailIDAlreadyPresentException("Email already registered");
     Seller seller= SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
   Seller savedSeller= sellerRepository.save(seller);

   SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(savedSeller);
   return sellerResponseDto;
    }
}
