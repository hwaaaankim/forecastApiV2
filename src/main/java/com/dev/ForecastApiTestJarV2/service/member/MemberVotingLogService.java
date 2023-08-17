package com.dev.ForecastApiTestJarV2.service.member;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.model.member.MemberVotingLog;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;
import com.dev.ForecastApiTestJarV2.repository.member.MemberVotingLogRepository;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestAnswerRepository;

@Service
public class MemberVotingLogService {

	@Autowired
	MemberVotingLogRepository memberVotingLogRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	QuestAnswerRepository questAnswerRepository;
	
	public MemberVotingLog memberVoting(HashMap<String, Object> result, String walletAddress) {
		
		
		int id = (int) result.get("ID");
		Long questId = Long.valueOf(id);
		int aId =  (int) result.get("AID");
		Long answerId = Long.valueOf(aId);
		int amount =  (int) result.get("AMOUNT");
		Long tokenAmount = Long.valueOf(amount);
		MemberVotingLog log = new MemberVotingLog();
		log.setVotingLogText("VOTING");
		log.setVotingLogDate(new Date());
		log.setVotingLogQuestId(questId);
		log.setVotingLogAnswer(questAnswerRepository.findById(answerId).get());
		log.setVotingLogMemberId(memberRepository.findOneByMemberWalletAddress(walletAddress).get().getMemberId());
		log.setVotingLogTokenAmount(tokenAmount);
		
		return memberVotingLogRepository.save(log);
	}
}
