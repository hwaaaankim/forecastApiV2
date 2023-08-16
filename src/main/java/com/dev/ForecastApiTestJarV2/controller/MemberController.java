package com.dev.ForecastApiTestJarV2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTestJarV2.dto.EmailDTO;
import com.dev.ForecastApiTestJarV2.dto.MemberLoginRequestDTO;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;
import com.dev.ForecastApiTestJarV2.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/member")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	
    @PostMapping("/signin")
    public ResponseEntity<Object> login(
    		@RequestBody MemberLoginRequestDTO memberLoginRequestDTO,
    		HttpServletRequest request
    		) {
    	
        log.info(memberLoginRequestDTO.getWalletAddress() + " Login Attempt.");
        return memberService.login(memberLoginRequestDTO, request);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Object> register(
    		@RequestBody MemberLoginRequestDTO memberLoginRequestDTO,
    		HttpServletRequest request
    		) {
    	
        log.info(memberLoginRequestDTO.getWalletAddress() + " Register Attempt.");
        return memberService.register(memberLoginRequestDTO, request);
    }
    
    @RequestMapping("/logout")
    public String logout() {
    	memberService.logout();
    	return "success";
    }
    
    @PostMapping("/memberEmailChecking")
    public String memberEmailChecking(
    		@RequestBody(required = false) EmailDTO email
    		) {
    	if(memberRepository.findOneByMemberEmail(email.getEmail()).isEmpty()) {
    		
    		return "N";
    	}else {
    		
    		return "Y";
    	}
    }
}
























