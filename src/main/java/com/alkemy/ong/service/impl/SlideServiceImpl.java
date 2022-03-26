package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.entity.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
public class SlideServiceImpl implements SlideService {
    @Autowired
    private SlideRepository slideRepository;
    @Override
    public void createSlide(SlideRequestDto slideRequestDto) throws Exception {
        /*Llamar AmazonService.uploadFile(pasando multipartFile o file)*/

        Slide slide = new Slide();
        slide.setImageUrl(slideRequestDto.getImgUrl());

        byte[] data = Base64.decodeBase64(slideRequestDto.getImgUrl());


        TransformBase64ToMulti transform = new TransformBase64ToMulti(data);
        transform.transferTo();
        Integer lugar;
        if (slideRequestDto.getOrder()==null || slideRequestDto.getOrder().equals("")){
            slide.setOrder(lugar = Math.toIntExact(slideRepository.count()));
        }
    }
}
