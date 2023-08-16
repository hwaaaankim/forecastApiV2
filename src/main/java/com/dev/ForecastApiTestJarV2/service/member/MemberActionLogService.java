package com.dev.ForecastApiTestJarV2.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.repository.member.MemberActionLogRepository;

@Service
public class MemberActionLogService {

	@Autowired
	MemberActionLogRepository memberActionLogRepository;
}
