package com.dev.ForecastApiTestJarV2.service.quest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.repository.quest.QuestHashTagRepository;

@Service
public class QuestHashTagService {

	@Autowired
	private QuestHashTagRepository questHashTagRepository;
}
