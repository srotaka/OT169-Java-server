package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;

public interface TestimonialService {

     TestimonialDto save(TestimonialDto testimonialDto);

    void delete(String id);

    TestimonialDto updateTestimonials(TestimonialDto testimonialDto, String id);

}
