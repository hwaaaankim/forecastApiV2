package com.dev.ForecastApiTestJarV2.service.member;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
import com.dev.ForecastApiTestJarV2.dto.MemberLoginRequestDTO;
import com.dev.ForecastApiTestJarV2.dto.TokenInfo;
import com.dev.ForecastApiTestJarV2.filter.JwtFilter;
import com.dev.ForecastApiTestJarV2.model.member.Member;
import com.dev.ForecastApiTestJarV2.model.member.MemberAccessLog;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;
import com.dev.ForecastApiTestJarV2.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final MemberAccessLogService memberAccessLogService;
//	private final RedisRepository redisRepository;
	private final RedisTemplate<String, String> redisTemplate;

	@Transactional
	public ResponseEntity<Object> login(
			MemberLoginRequestDTO memberLoginRequestDTO,
			HttpServletRequest request
			) {
//		System.out.println("login");
		MemberAccessLog accessLog = new MemberAccessLog();
		if (memberRepository.findOneByMemberWalletAddress(memberLoginRequestDTO.getWalletAddress()).orElse(null) != null) {
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(memberLoginRequestDTO.getWalletAddress(), memberLoginRequestDTO.getWalletId());
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			TokenInfo jwt = jwtTokenProvider.generateToken(authentication, "EXISTING USER");
			log.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), request.getRequestURI());
			accessLog.setAccessLogIp(request.getRemoteAddr());
			accessLog.setAccessLogAccessJwt(jwt.getAccessToken());
			accessLog.setAccessLogRefreshJwt(jwt.getRefreshToken());
			accessLog.setAccessLogMemberId(memberRepository.findOneByMemberWalletAddress(memberLoginRequestDTO.getWalletAddress()).get().getMemberId());
			accessLog.setAccessLogText("EXISTING USER");
			memberAccessLogService.saveAccessLog(accessLog);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
			
//			MemberJWTRedis redis = new MemberJWTRedis();
//			redis.setDate(new Date());
//			redis.setAccessJwt(jwt.getAccessToken());
//			redis.setRefreshJwt(jwt.getRefreshToken());
//			redis.setWalletAddress(authentication.getName());
//			redisRepository.save(redis);
			redisTemplate.opsForValue().set(authentication.getName(), jwt.getAccessToken());
//			System.out.println(redisTemplate.opsForValue().get(authentication.getName()));
			return new ResponseEntity<Object>(jwt, HttpStatus.valueOf(200));
		}else {
			
			return new ResponseEntity<Object>("Input your Email", HttpStatus.valueOf(200));
		}
	}

	@Transactional
	public void logout() {
        //Token에서 로그인한 사용자 정보 get해 로그아웃 처리
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//		 System.out.println("logout" + redisTemplate.opsForValue().get(authentication.getName()));
        if (redisTemplate.opsForValue().get(authentication.getName()) != null) {
            redisTemplate.delete(authentication.getName()); //Token 삭제
        }
//        System.out.println("logout" + redisTemplate.opsForValue().get(authentication.getName()));
	}
	
	@Transactional
	public ResponseEntity<Object> register(
			MemberLoginRequestDTO memberLoginRequestDTO,
			HttpServletRequest request
			) {
		System.out.println("register");
		MemberAccessLog accessLog = new MemberAccessLog();
		Member member = Member.builder()
				.memberWalletAddress(memberLoginRequestDTO.getWalletAddress())
				.memberWalletId(passwordEncoder.encode(memberLoginRequestDTO.getWalletId()))
				.memberRole("ROLE_USER")
                .memberJoinDate(new Date())
                .memberEmail(memberLoginRequestDTO.getEmail())
                .memberNickname(memberLoginRequestDTO.getEmail())
				.memberActivated(true)
				.memberSign(true).build();
		memberRepository.save(member);
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(memberLoginRequestDTO.getWalletAddress(), memberLoginRequestDTO.getWalletId());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		TokenInfo jwt = jwtTokenProvider.generateToken(authentication, "NEW USER");
		log.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), request.getRequestURI());
		accessLog.setAccessLogIp(request.getRemoteAddr());
		accessLog.setAccessLogAccessJwt(jwt.getAccessToken());
		accessLog.setAccessLogRefreshJwt(jwt.getRefreshToken());
		accessLog.setAccessLogMemberId(memberRepository.findOneByMemberWalletAddress(memberLoginRequestDTO.getWalletAddress()).get().getMemberId());
		accessLog.setAccessLogText("NEW USER");
		memberAccessLogService.saveAccessLog(accessLog);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		
//		MemberJWTRedis redis = new MemberJWTRedis();
//		redis.setDate(new Date());
//		redis.setAccessJwt(jwt.getAccessToken());
//		redis.setRefreshJwt(jwt.getRefreshToken());
//		redis.setWalletAddress((String)authentication.getPrincipal());
//		redisRepository.save(redis);
		redisTemplate.opsForValue().set(authentication.getName(), jwt.getAccessToken());
		return new ResponseEntity<Object>(jwt, HttpStatus.valueOf(200));
	}
	
	// 유저,권한 정보를 가져오는 메서드
	@Transactional(readOnly = true)
	public Optional<Member> getUserWithAuthorities(String walletAddress) {
		return memberRepository.findOneByMemberWalletAddress(walletAddress);
	}

	// 현재 securityContext에 저장된 Wallet Address의 정보만 가져오는 메서드
	@Transactional(readOnly = true)
	public Optional<Member> getMyUserWithAuthorities() {
		return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneByMemberWalletAddress);
	}

}
