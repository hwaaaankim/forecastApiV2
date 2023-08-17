package com.dev.ForecastApiTestJarV2.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
import com.dev.ForecastApiTestJarV2.service.member.MemberActionLogService;
import com.dev.ForecastApiTestJarV2.service.member.MemberVotingLogService;
import com.dev.ForecastApiTestJarV2.service.quest.QuestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/dao")
@Slf4j
@RequiredArgsConstructor
public class DaoController {

	private final QuestService questService;
	private final MemberActionLogService memberActionLogService;
	private final MemberVotingLogService memberVotingLogService;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@RequestMapping("/action")
	public @ResponseBody Object questAction(
			@RequestBody HashMap<String, Object> result,
			HttpServletRequest request
			) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		String token = "";
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			token = bearerToken.split(" ")[1].trim();
		}
		String walletAddress = jwtTokenProvider.getAuthentication(token).getName();
		questService.questAction(result , walletAddress);
		return memberActionLogService.memberAction(result, walletAddress);
	}
	
	@RequestMapping("/voting")
	public @ResponseBody Object questVoting(
			@RequestBody HashMap<String, Object> result,
			HttpServletRequest request
			) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		String token = "";
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			token = bearerToken.split(" ")[1].trim();
		}
		String walletAddress = jwtTokenProvider.getAuthentication(token).getName();
		questService.questVoting(result , walletAddress);
		memberVotingLogService.memberVoting(result, walletAddress);
		return memberVotingLogService.memberVoting(result, walletAddress);
	}
}

















