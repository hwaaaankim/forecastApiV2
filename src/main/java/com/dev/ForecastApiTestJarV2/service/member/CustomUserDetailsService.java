package com.dev.ForecastApiTestJarV2.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ForecastApiTestJarV2.model.member.Member;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public User loadUserByUsername(final String walletAddress) {
    	
        return  memberRepository.findOneByMemberWalletAddress(walletAddress)
                .map(user -> createUser(walletAddress, user))
                .orElseThrow(() -> new UsernameNotFoundException(walletAddress + " -> 데이터베이스에서 찾을 수 없습니다."));
    	
    }

    private org.springframework.security.core.userdetails.User createUser(String walletAddress, Member member) {
    	if (!member.isMemberActivated()) {
            throw new RuntimeException(walletAddress + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(member.getMemberRole()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
        		member.getMemberWalletAddress(),
        		member.getMemberWalletId(),
        		true, true, true, true, grantedAuthorities);
    }
}
