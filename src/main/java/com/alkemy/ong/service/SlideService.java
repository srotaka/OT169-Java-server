package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;

public interface SlideService {


    void createSlide(SlideRequestDto slideRequestDto) throws Exception;

    SlideResponseDto getSlideDetails (String id) throws Exception;
}
