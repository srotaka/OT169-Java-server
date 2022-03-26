package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.repository.SlideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/Slide")
public class SlideController {

    @Autowired
    private SlideRepository slideRepository;

    @PostMapping
    private void createSlide(@Valid @RequestBody SlideRequestDto slideRequestDto){








    }
}
