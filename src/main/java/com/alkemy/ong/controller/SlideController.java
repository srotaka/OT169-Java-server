package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequestDto;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private SlideService slideService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    private ResponseEntity<Void> createSlide(@Valid @RequestBody SlideRequestDto slideRequestDto){
        try {
            slideService.createSlide(slideRequestDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

       return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
