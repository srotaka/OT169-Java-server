package com.alkemy.ong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alkemy.ong.entity.Member;
import com.alkemy.ong.repository.MemberRepository;
import java.util.*;

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
	public ResponseEntity<?> getAllMembers(){
		List<Member> memberList = memberRepository.findAll();
		Map<String, String> memberInfoMap = new LinkedHashMap<>();

		if(memberList.size() < 0 || memberList.isEmpty()){
			return ResponseEntity.noContent().build();
		}
		for (Member member: memberList) {
			memberInfoMap.put(member.getName(), member.getDescription());
		}
		return ResponseEntity.ok(memberInfoMap);
	}

	//Exists
	public boolean existsById(String id) {
		return memberRepository.existsById(id);
	}

	public Member getById(String id){
		return memberRepository.getById(id);
	}
}
