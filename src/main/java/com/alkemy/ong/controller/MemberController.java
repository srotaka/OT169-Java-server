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

	@PutMapping("/{id}")//OT169-71
	public ResponseEntity<Member> updateMember(@RequestParam(name="id") String id,@RequestBody Member member){
		if(memberService.existsById(id)) {//If the member exists
			return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);//I create the member
		}
		return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);//If it doesn't or the Member is null/not valid, I throw 404 error code
	}
	
}
