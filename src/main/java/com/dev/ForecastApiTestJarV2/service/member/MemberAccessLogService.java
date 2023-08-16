package com.dev.ForecastApiTestJarV2.service.member;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.ForecastApiTestJarV2.model.member.MemberAccessLog;
import com.dev.ForecastApiTestJarV2.repository.member.MemberAccessLogRepository;

@Service
public class MemberAccessLogService {

	@Autowired
	MemberAccessLogRepository memberAccessLogRepository;
	
	@Transactional(readOnly = true)
	@ResponseBody
	public MemberAccessLog saveAccessLog(MemberAccessLog accessLog){
		
		MemberAccessLog access = new MemberAccessLog();
		access.setAccessLogIp(accessLog.getAccessLogIp());
		access.setAccessLogAccessJwt(accessLog.getAccessLogAccessJwt());
		access.setAccessLogRefreshJwt(accessLog.getAccessLogRefreshJwt());
		access.setAccessLogMemberId(accessLog.getAccessLogMemberId());
		access.setAccessLogText(accessLog.getAccessLogText());
		access.setAccessLogSign(true);
		access.setAccessLogDate(new Date());
		
		return memberAccessLogRepository.save(access);
	}
}
