package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;

import java.util.List;

public interface SlideService {


    void createSlide(SlideRequestDto slideRequestDto) throws Exception;

    SlideResponseDto getSlideDetails (String id) throws Exception;

    List<SlideDto> findAll() throws Exception;

    void deleteSlide (String id) throws Exception;

    List<SlideResponseDto> slideForOng(String idOng) throws Exception;

}
