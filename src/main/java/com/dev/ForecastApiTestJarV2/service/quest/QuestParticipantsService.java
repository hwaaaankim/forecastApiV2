package com.dev.ForecastApiTestJarV2.service.quest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.repository.quest.QuestParticipantsRepository;

@Service
public class QuestParticipantsService {

	@Autowired
	private QuestParticipantsRepository questParticipantsRepository;
}
