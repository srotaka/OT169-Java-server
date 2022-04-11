package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, String> {

    @Query(value="SELECT id, name, content, image FROM testimonials",nativeQuery=true)
    Page<List<LinkedHashMap>> findPage(Pageable pageable);

}
