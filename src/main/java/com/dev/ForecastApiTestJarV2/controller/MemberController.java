package com.dev.ForecastApiTestJarV2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
import com.dev.ForecastApiTestJarV2.dto.MemberLoginRequestDTO;
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
    	System.out.println("signin");
        String username = memberLoginRequestDTO.getUsername();
        String password = memberLoginRequestDTO.getPassword();
       
        return memberService.login(username, password);
        
//        return new ResponseEntity<Object>(tokenInfo, HttpStatus.valueOf(200));
    }
    
//    @PostMapping("/signup")
//    public ResponseEntity<TokenInfo> signup(
//    		@RequestBody MemberLoginRequestDTO memberLoginRequestDTO) {
//    	System.out.println("signin");
//        String username = memberLoginRequestDTO.getUsername();
//        String password = memberLoginRequestDTO.getPassword();
//        TokenInfo tokenInfo = memberService.login(username, password);
//        
//    	
//        return tokenInfo;
//    }
}
























