package com.dev.ForecastApiTestJarV2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
import com.dev.ForecastApiTestJarV2.dto.MemberLoginRequestDTO;
import com.dev.ForecastApiTestJarV2.model.member.Member;
import com.dev.ForecastApiTestJarV2.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/member")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/signin")
    public ResponseEntity<Object> login(@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {
        String walletAddress = memberLoginRequestDTO.getWalletAddress();
        String walletId = memberLoginRequestDTO.getWalletId();
       
        return memberService.login(walletAddress, walletId);
        
//        return new ResponseEntity<Object>(tokenInfo, HttpStatus.valueOf(200));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(
    		@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {
        
    	
        return ResponseEntity.ok(memberService.signup(memberLoginRequestDTO));
    }
    
    @GetMapping("/test")
    public String test() {
        
    	System.out.println("hi");
        return "success";
    }
}
























