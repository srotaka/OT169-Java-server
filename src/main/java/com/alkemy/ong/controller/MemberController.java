package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MembersResponseDto;
import io.swagger.annotations.*;
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
@Api(tags = "Member Controller", value = "MemberEndpoints")
public class MemberController {
	
	@Autowired
	private MemberService memberService;


	@PostMapping(produces = {"application/json"}, consumes = {"application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create a member and return it.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "CREATED - The member was successfully created",
					response = Member.class),
			@ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
					+ "cannot be empty or null."),
			@ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.")})
	@ApiImplicitParam(name = "Authorization", value = "Access Token",
			required = true,
			allowEmptyValue = false,
			paramType = "header",
			dataTypeClass = String.class,
			example = "Bearer access_token")
	public ResponseEntity<Member> createMember(@RequestBody Member member) { // I get the entity
		return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK); // if it doesn't had errors,
	}

	@DeleteMapping(value = "/{id}", produces = {"application/json"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a member passed by id.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO_CONTENT - The member was successfully deleted"),
			@ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden."),
			@ApiResponse(code = 404, message = "NOT_FOUND - Member not found.")})
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "id",
					value = "Id of the member we want to delete",
					required = true, allowEmptyValue = false,
					paramType = "path", dataTypeClass = String.class,
					example = "1"),
			@ApiImplicitParam(name = "Authorization",
					value = "Access Token",
					required = true,
					allowEmptyValue = false,
					paramType = "header",
					dataTypeClass = String.class,
					example = "Bearer access_token")})
	public ResponseEntity<Member> deleteMember(@PathVariable String id){
		if(memberService.existsById(id)) {//If the member exists
			memberService.delete( memberService.getById(id) );//I delete the member
			return new ResponseEntity<Member>(HttpStatus.OK);//I send an OK(200) code
		}
		return new ResponseEntity<Member>(HttpStatus.INTERNAL_SERVER_ERROR);//If the member doesn't exists, throws 500 error code
	}


	@PutMapping("/{id}")//OT169-71
	public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Member member){

	@PutMapping(value = "/{id}", produces = {"application/json"}, consumes = {"application/json"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update a member passed by id.")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO_CONTENT - The member was successfully updated"),
			@ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
					+ "cannot be empty or null."),
			@ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden."),
			@ApiResponse(code = 404, message = "NOT_FOUND - Member not found.")})
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "id",
					value = "Id of the member we want to update",
					required = true, allowEmptyValue = false,
					paramType = "path", dataTypeClass = String.class,
					example = "1"),
			@ApiImplicitParam(name = "Authorization",
					value = "Access Token",
					required = true,
					allowEmptyValue = false,
					paramType = "header",
					dataTypeClass = String.class,
					example = "Bearer access_token")})
	public ResponseEntity<Member> updateMember(@RequestParam(name="id") String id,@RequestBody Member member){

		if(memberService.existsById(id)) {//If the member exists
			return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);//I create the member
		}
		return ResponseEntity.status(NOT_FOUND).build();//If it doesn't or the Member is null/not valid, I throw 500 error code

	}

	@GetMapping(produces = {"application/json"})
	@ApiOperation(value = "Return member list per page.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK - The list of members. The size of the page is the one"
					+ "passed in the parameters", response = MembersResponseDto.class, responseHeaders = {
					@ResponseHeader(name = "Link",
							description = "Link of the previous page and another for the next page",
							response = String.class)}),
			@ApiResponse(code = 401, message = "Unauthorized")})
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "page", value = "Page of the list",
					required = true,
					paramType = "query",
					dataTypeClass = String.class,
					example = "0"),
			@ApiImplicitParam(name = "size",
					value = "Size of the page",
					required = false,
					paramType = "query",
					dataTypeClass = String.class,
					example = "10"),
			@ApiImplicitParam(name = "Authorization",
					value = "Access Token",
					required = true,
					allowEmptyValue = false,
					paramType = "header",
					dataTypeClass = String.class,
					example = "Bearer access_token")})
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "0") Integer page){
		try{
			return ResponseEntity.status(OK).body(memberService.getAllMembers(page));
		}catch (Exception e){
			return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
		}
	}
}
