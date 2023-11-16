package com.ssafy.triends.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.triends.member.model.Member;
import com.ssafy.triends.member.model.service.MemberService;
import com.ssafy.triends.util.JWTUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	private JWTUtil jwtUtil;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<Member> login(@RequestBody Member member) {
		System.out.println(member);
		return ResponseEntity.ok().body(memberService.login(member.getUserId(), member.getUserPass()));
	}
	
	@PostMapping
	public ResponseEntity<String> regist(@RequestBody Member member) {
		memberService.regist(member);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
