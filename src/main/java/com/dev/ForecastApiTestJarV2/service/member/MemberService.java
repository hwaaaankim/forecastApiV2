package com.dev.ForecastApiTestJarV2.service.member;

import java.util.Date;
import java.util.Optional;

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
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;
import com.dev.ForecastApiTestJarV2.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public ResponseEntity<Object> login(String walletAddress, String walletId) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(walletAddress,
				walletId);
		System.out.println(authenticationToken);
			Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
			System.out.println("123123123" + authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			TokenInfo jwt = jwtTokenProvider.generateToken(authentication);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
			return new ResponseEntity<Object>(jwtTokenProvider.generateToken(authentication), HttpStatus.valueOf(200));

	}

	@Transactional
	public Member signup(MemberLoginRequestDTO memberLoginRequestDTO) {
		if (memberRepository.findOneByMemberWalletAddress(memberLoginRequestDTO.getWalletAddress()).orElse(null) != null) {
			throw new RuntimeException("이미 가입되어 있는 유저입니다.");
		}

		// 유저 정보를 만들어서 save
		Member member = Member.builder()
				.memberWalletAddress(memberLoginRequestDTO.getWalletAddress())
				.memberWalletId(passwordEncoder.encode(memberLoginRequestDTO.getWalletId()))
//				.roles(memberLoginRequestDTO.getRoles())
                .memberJoinDate(new Date())
                .memberEmail(memberLoginRequestDTO.getEmail())
                .memberNickname(memberLoginRequestDTO.getEmail())
                .memberRole("ADMIN")
				.memberActivated(true).build();

		return memberRepository.save(member);
	}

	// 유저,권한 정보를 가져오는 메소드
	@Transactional(readOnly = true)
	public Optional<Member> getUserWithAuthorities(String walletAddress) {
		return memberRepository.findOneByMemberWalletAddress(walletAddress);
	}

	// 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
	@Transactional(readOnly = true)
	public Optional<Member> getMyUserWithAuthorities() {
		System.out.println("getMyUserWithAuthorities");
		return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneByMemberWalletAddress);
	}

}
