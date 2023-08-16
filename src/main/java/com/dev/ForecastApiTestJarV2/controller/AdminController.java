package com.dev.ForecastApiTestJarV2.controller;

import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTestJarV2.model.member.Member;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/admin")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {

	private final MemberRepository memberRepository;
	
	@GetMapping("/selectMemberAll")
	public @ResponseBody Object selectMemberDetail(
			@PageableDefault(size = 10) Pageable pageable
			) {
		
		Page<Member> members = memberRepository.findAll(pageable);
		int startPage = Math.max(1, members.getPageable().getPageNumber() - 4);
		int endPage = Math.min(members.getTotalPages(), members.getPageable().getPageNumber() + 4);
		HashMap<String, Object> member = new HashMap<String, Object>();
		member.put("members", members);
		member.put("startPage", startPage);
		member.put("endPage", endPage);
		member.put("totalPage", members.getPageable());
		member.put("totalElements", members.getTotalElements());
		return member;
	}
	
	@GetMapping("/selectMemberDetail/{address}")
	public @ResponseBody Object selectMemberDetail(
			@PathVariable String address
			) {
		
		return memberRepository.findOneByMemberWalletAddress(address);
	} 
}




















