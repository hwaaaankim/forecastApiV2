package com.dev.ForecastApiTestJarV2.service.quest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.model.quest.Quest;
import com.dev.ForecastApiTestJarV2.model.quest.QuestAnswer;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestAnswerRepository;

@Service
public class QuestAnswerService {

	@Autowired
	private QuestAnswerRepository questAnswerRepository;
	
	public void questAnswerRegistration(Quest quest, List<String> answers) {
		for(String answer : answers) {
			QuestAnswer a = new QuestAnswer();
			a.setAnswerQuestId(quest.getQuestId());
			a.setAnswerValue(answer);
			
			questAnswerRepository.save(a);
		}
	}
}
