package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.entity.Member;
import com.alkemy.ong.service.MemberService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

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
		return new ResponseEntity<Member>(HttpStatus.BAD_REQUEST);//If it doesn't or the Member is null/not valid, I throw 500 error code

	}

	@GetMapping() // OT169-68
	public ResponseEntity<?> getAllUsers(){
		try{
			return ResponseEntity.status(OK).body(memberService.getAllMembers());
		}catch (Exception e){
			return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
		}
	}
	
}
