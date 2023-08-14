//package com.dev.ForecastApiTestJarV2.backup;
//
//import java.util.Date;
//import java.util.Optional;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
//import com.dev.ForecastApiTestJarV2.dto.MemberLoginRequestDTO;
//import com.dev.ForecastApiTestJarV2.dto.TokenInfo;
//import com.dev.ForecastApiTestJarV2.filter.JwtFilter;
//import com.dev.ForecastApiTestJarV2.model.member.Member;
//import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;
//import com.dev.ForecastApiTestJarV2.utils.SecurityUtil;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class MemberServiceEX {
//
//	private final MemberRepository memberRepository;
//	private final AuthenticationManagerBuilder authenticationManagerBuilder;
//	private final JwtTokenProvider jwtTokenProvider;
//	private final PasswordEncoder passwordEncoder;
//
//	@Transactional
//	public ResponseEntity<Object> login(String walletAddress, String walletId) {
//		// 1. Login ID/PW 를 기반으로 Authentication 객체 생성
//		// 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(walletAddress,
//				walletId);
//		System.out.println(authenticationToken);
//// authenticate 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
////        try {
//// 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
//		// authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername
//		// 메서드가 실행
////		try {
//			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//			// 해당 객체를 SecurityContextHolder에 저장하고
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			// authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
//			TokenInfo jwt = jwtTokenProvider.generateToken(authentication);
//			HttpHeaders httpHeaders = new HttpHeaders();
//			// response header에 jwt token에 넣어줌
//			httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//			// tokenInfo를 이용해 response body에도 넣어서 리턴
//			return new ResponseEntity<Object>(jwtTokenProvider.generateToken(authentication), HttpStatus.valueOf(200));
////		} catch (BadCredentialsException e) {
////			System.out.println(e.getMessage());
////			Map<String, Object> result = new HashMap<>();
////          	result.put("status", ApiErrorResponse.PASSWORD_ERROR.getCode());
////            result.put("message", ApiErrorResponse.PASSWORD_ERROR.getMessage());
////            return new ResponseEntity<Object>(result, HttpStatus.valueOf(403));
////		}
//
////        }catch(UsernameNotFoundException e) {
////    		System.out.println("UsernameNotFoundException");
////    		Map<String, Object> result = new HashMap<>();
////            result.put("status", 1003);
////            result.put("message", e.getMessage());
////            return new ResponseEntity<>(result, HttpStatus.valueOf(404));
////    	}catch(BadCredentialsException e) {
////    		System.out.println("BadCredentialsException");
////    		Map<String, Object> result = new HashMap<>();
////            result.put("status", 1004);
////            result.put("message", e.getMessage());
////            return new ResponseEntity<>(result, HttpStatus.valueOf(404));
////    	}
//
//	}
//
//	@Transactional
//	public Member signup(MemberLoginRequestDTO memberLoginRequestDTO) {
//		if (memberRepository.findOneByMemberWalletAddress(memberLoginRequestDTO.getWalletAddress()).orElse(null) != null) {
//			throw new RuntimeException("이미 가입되어 있는 유저입니다.");
//		}
//
//		// 유저 정보를 만들어서 save
//		Member member = Member.builder()
//				.memberWalletAddress(memberLoginRequestDTO.getWalletAddress())
//				.memberWalletId(memberLoginRequestDTO.getWalletId())
////				.roles(memberLoginRequestDTO.getRoles())
//                .memberJoinDate(new Date())
//                .memberEmail(memberLoginRequestDTO.getEmail())
//                .memberNickname(memberLoginRequestDTO.getEmail())
//                .memberRole("ADMIN")
//				.memberActivated(true).build();
//
//		return memberRepository.save(member);
//	}
//
//	// 유저,권한 정보를 가져오는 메소드
//	@Transactional(readOnly = true)
//	public Optional<Member> getUserWithAuthorities(String walletAddress) {
//		return memberRepository.findOneByMemberWalletAddress(walletAddress);
//	}
//
//	// 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
//	@Transactional(readOnly = true)
//	public Optional<Member> getMyUserWithAuthorities() {
//		System.out.println("getMyUserWithAuthorities");
//		return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneByMemberWalletAddress);
//	}
//
//}
