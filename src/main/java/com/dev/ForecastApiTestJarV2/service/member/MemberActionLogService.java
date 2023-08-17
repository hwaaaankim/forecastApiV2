package com.dev.ForecastApiTestJarV2.service.member;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.model.member.MemberActionLog;
import com.dev.ForecastApiTestJarV2.repository.member.MemberActionLogRepository;
import com.dev.ForecastApiTestJarV2.repository.member.MemberRepository;

@Service
public class MemberActionLogService {

	@Autowired
	MemberActionLogRepository memberActionLogRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	public MemberActionLog memberAction(HashMap<String, Object> result, String walletAddress) {
		
		
		int id = (int) result.get("ID");
		Long questId = Long.valueOf(id);
		Boolean code =  (Boolean) result.get("CODE");
		MemberActionLog log = new MemberActionLog();
		log.setActionLogSign(code);
		log.setActionLogText("ACTION");
		log.setActionLogDate(new Date());
		log.setActionLogQuestId(questId);
		log.setActionLogMemberId(memberRepository.findOneByMemberWalletAddress(walletAddress).get().getMemberId());
		log.setActionLogTokenAmount(0L);
		
		return memberActionLogRepository.save(log);
	}
}
