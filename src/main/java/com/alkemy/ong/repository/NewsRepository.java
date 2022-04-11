package com.alkemy.ong.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alkemy.ong.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, String>{

    @Query(value="SELECT id, name, content, image from News",nativeQuery=true)
    Page<List<LinkedHashMap>> findPage(Pageable paging);

    Page<News> findAll(Pageable pageable);
}
