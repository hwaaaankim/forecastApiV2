package com.dev.ForecastApiTestJarV2.service.quest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.model.quest.Quest;
import com.dev.ForecastApiTestJarV2.model.quest.QuestImages;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestImagesRepository;

@Service
public class QuestImagesService {

	@Autowired
	private QuestImagesRepository questImagesRepository;
	
	public void questImagesRegistration(List<String> filePath, Quest quest) {
	
		for(String path : filePath) {
			QuestImages q = new QuestImages();
			q.setQuestImagesName("QUEST IMAGE");
			q.setQuestImagesPath(path);
			q.setQuestImagesRoad(path);
			q.setQuestImagesQuestId(quest.getQuestId());
			
			questImagesRepository.save(q);
		}
	}
}
