package com.alkemy.ong.repository;

import com.alkemy.ong.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
}
