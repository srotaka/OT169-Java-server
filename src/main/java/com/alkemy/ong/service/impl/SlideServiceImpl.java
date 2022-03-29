package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.entity.Slide;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AmazonService;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.utils.CustomMultipartFile;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64.Encoder;
import java.util.Optional;

@Service
public class SlideServiceImpl implements SlideService {
    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AmazonService amazonService;



    @Override
    public void createSlide(SlideRequestDto slideRequestDto) throws Exception {

        Slide slide = new Slide();
        Integer dOrder;
        if (slideRequestDto.getOrder()==null){
              dOrder= Math.toIntExact(slideRepository.count()+1);
              slide.setOrder(dOrder);
        }else {
            slide.setOrder(slideRequestDto.getOrder());
        }
        /*
        * Transform Base64 to MultipartFile, send to AmazonService*/
        String nameFile = "Slide "+slide.getOrder();
        MultipartFile imgSend= this.base64ToImage(slideRequestDto.getImgUrl(),nameFile);
        String amazonUrl=amazonService.uploadFile(imgSend);
        /*
        * find organization to add to slide*/
        Optional<OrganizationEntity> orgFind = organizationRepository.findById(slideRequestDto.getOrganizationId());
        if (!orgFind.isPresent()){
            throw new Exception("Organization not found in Slide creation");
        }else {
            slide.setOrganizationId(orgFind.get());
        }

        slide.setImageUrl(amazonUrl);
        slide.setText(slideRequestDto.getText());

        slideRepository.save(slide);







    }

    public MultipartFile base64ToImage(String encoded, String fileName) {

        // We need to remove "data:image/jpeg;base64", just to keep the bytes to decode
        String trimmedEncodedImage = encoded.substring(encoded.indexOf(",") + 1);

        byte[] decodedBytes = Base64.decodeBase64(trimmedEncodedImage);

        CustomMultipartFile customMultipartFile = new CustomMultipartFile(decodedBytes, fileName);

        try {
            customMultipartFile.transferTo(customMultipartFile.getFile());
        } catch (IllegalStateException e) {
            System.out.println("IllegalStateException : " + e);
        } catch (IOException e) {
            System.out.println("IOException : " + e);
        }

        return customMultipartFile;
    }
}
