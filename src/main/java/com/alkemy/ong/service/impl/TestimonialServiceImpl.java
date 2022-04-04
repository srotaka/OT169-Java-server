package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.entity.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public TestimonialDto save(TestimonialDto testimonialDto) {
        Testimonial testimonial = mapper.mapFromDto(testimonialDto, new Testimonial());
        Testimonial saved = testimonialRepository.save(testimonial);
        TestimonialDto result = mapper.mapToDto(saved, new TestimonialDto());

        return result;
    }

    @Override
    public void delete(String id) {
        Optional<Testimonial> optional = testimonialRepository.findById(id);

        if (optional.isPresent()) {
            testimonialRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}
