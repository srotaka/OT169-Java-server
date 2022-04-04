package com.alkemy.ong.controller;


import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {


    @Autowired
    private TestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<TestimonialDto> save(@Valid @RequestBody TestimonialDto testimonialDto){
        TestimonialDto savedTestimonials = testimonialService.save(testimonialDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTestimonials);
    }


}
