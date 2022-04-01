package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.entity.Member;
import com.alkemy.ong.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@DeleteMapping("/{id}")//OT169-70
	public ResponseEntity<Member> createMember(@RequestParam(name="id") String id, @RequestBody Member member){
		if(memberService.existsById(id)) {//If the member exists
			memberService.delete(member);//I delete the member
			return new ResponseEntity<Member>(HttpStatus.OK);//I send an OK(200) code
		}
		return new ResponseEntity<Member>(HttpStatus.INTERNAL_SERVER_ERROR);//If the member doesn't exists, throws 500 error code
	}
	
}
