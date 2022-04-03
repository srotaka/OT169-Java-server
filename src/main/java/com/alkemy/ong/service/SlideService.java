package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.dto.SlideResponseDto;
import com.alkemy.ong.dto.SlideUpdateDto;

import java.util.List;

public interface SlideService {


    void createSlide(SlideRequestDto slideRequestDto) throws Exception;

    SlideResponseDto getSlideDetails (String id) throws Exception;

    List<SlideDto> findAll() throws Exception;

    void deleteSlide (String id) throws Exception;

    void updateSlide (String id, SlideUpdateDto update) throws Exception;
}
