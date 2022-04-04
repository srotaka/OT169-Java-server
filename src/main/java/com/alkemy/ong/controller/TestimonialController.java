package com.alkemy.ong.controller;


import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        testimonialService.delete(id);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestimonialDto> updateTestimonials(@Valid @RequestBody TestimonialDto testimonialDto, @PathVariable String id) throws ResponseStatusException{
        try {
            testimonialService.updateTestimonials(testimonialDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(testimonialDto);
        }   catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }


}
