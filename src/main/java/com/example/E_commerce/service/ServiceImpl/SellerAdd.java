package com.example.E_commerce.service.ServiceImpl;

import com.example.E_commerce.Exception.EmailAlreadyPresentException;
import com.example.E_commerce.Exception.SellerNotFoundException;
import com.example.E_commerce.dto.requestDto.SellerRequestDto;
import com.example.E_commerce.dto.responseDto.SellerResponseDto;
import com.example.E_commerce.model.Seller;
import com.example.E_commerce.repository.SellerRepository;
import com.example.E_commerce.service.SellerService;
import com.example.E_commerce.transeformer.SellerTransformer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SellerAdd implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto sellerAdd(SellerRequestDto sellerRequestDto) throws EmailAlreadyPresentException {
        if (sellerRepository.findByEmailId(sellerRequestDto.getEmailId())!=null)
            throw new EmailAlreadyPresentException("Email already registered");

     Seller seller= SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
   Seller savedSeller= sellerRepository.save(seller);

   SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(savedSeller);
   return sellerResponseDto;
    }

    @Override
    public SellerResponseDto getSellerByEmail(String emailId) throws SellerNotFoundException {
        Seller seller=sellerRepository.findByEmailId(emailId);
        if (seller==null){
            throw new SellerNotFoundException("Seller with Email"+emailId+"not Found");
        }SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);
        return sellerResponseDto;

    }

    @Override
    public SellerResponseDto getSellerById(int id) throws SellerNotFoundException {
       Seller seller=sellerRepository.findById(id).get();
       if (seller==null){
           throw new SellerNotFoundException("Seller with id"+id+"not found");
       }
       SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);
       return sellerResponseDto;
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller> sellers=sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtos=new ArrayList<>();
        for(Seller seller:sellers){
            SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);
            sellerResponseDtos.add(sellerResponseDto);
        }
        return sellerResponseDtos;
    }

    @Override
    public SellerResponseDto changeByEmail(String emailId,SellerRequestDto sellerRequestDto) throws SellerNotFoundException {
        Seller seller = sellerRepository.findByEmailId(emailId);
        if (seller == null) {
            throw new SellerNotFoundException("Seller with email " + emailId + " not found");
        }
     seller.setName(sellerRequestDto.getName());
     seller.setAge(sellerRequestDto.getAge());
     seller.setMob_No(sellerRequestDto.getMob_No());


       //update seller
     Seller updatedSeller=sellerRepository.save(seller);
     return SellerTransformer.SellerToSellerResponseDto(updatedSeller);
    }

    @Override
    @Transactional
    public SellerResponseDto deleteByEmailId(String emailId) throws SellerNotFoundException {
      Seller seller=sellerRepository.findByEmailId(emailId);
      if (seller==null){
          throw new SellerNotFoundException("seller with emailId"+emailId+"not found");
      }
      sellerRepository.deleteByEmailId(emailId);
return SellerTransformer.SellerToSellerResponseDto(seller);
    }

    @Override
    @Transactional
    public SellerResponseDto deleteById(int id) throws SellerNotFoundException {
        Seller seller=sellerRepository.findById(id).get();
        if(seller==null){
            throw new SellerNotFoundException("Seller with Email"+id+"not found");
        }
        sellerRepository.deleteById(id);

        return SellerTransformer.SellerToSellerResponseDto(seller);
    }

    @Override
    public List<SellerResponseDto> getByAge(int age) throws SellerNotFoundException {
        List<Seller> sellers= sellerRepository.findByAge(age);
        if (sellers.isEmpty()){
            throw new SellerNotFoundException("Seller with age"+age+"not found");
        }
        List<SellerResponseDto> sellerResponseDtos=new ArrayList<>();
      for (Seller seller:sellers){
          SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);
          sellerResponseDtos.add(sellerResponseDto);
      }
       return sellerResponseDtos;
    }

}
