package com.alkemy.ong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alkemy.ong.entity.Member;
import com.alkemy.ong.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	//Create / Update
	public Member save(Member member) {
		return memberRepository.save(member);	
	}
	
	//Delete
	public ResponseEntity<Member> delete(Member member) {
		memberRepository.delete(member);	
		return new ResponseEntity<Member>(HttpStatus.OK);
	}
	//List all
	
	//Exists
	public boolean existsById(String id) {
		return memberRepository.existsById(id);
	}
}
