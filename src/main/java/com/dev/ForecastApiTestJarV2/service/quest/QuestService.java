package com.dev.ForecastApiTestJarV2.service.quest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.ForecastApiTestJarV2.dto.QuestDTO;
import com.dev.ForecastApiTestJarV2.model.quest.Quest;
import com.dev.ForecastApiTestJarV2.model.quest.QuestHashTag;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestRepository;

@Service
public class QuestService {

	@Autowired
	private QuestRepository questRepository;
	
	
	public void questVoting(HashMap<String, Object> result, String walletAddress) {
		int id = (int) result.get("ID");
		Long questId = Long.valueOf(id);
		int amount =  (int) result.get("AMOUNT");
		Long tokenAmount = Long.valueOf(amount);
		
		questRepository.findById(questId).ifPresent(q->{
			q.setQuestActionTokenAmount(q.getQuestVotingTokenAmount() + tokenAmount);
			questRepository.save(q);
		});
	}
	
	public void questAction(HashMap<String, Object> result, String walletAddress) {
		
		int id = (int) result.get("ID");
		Long questId = Long.valueOf(id);
		Boolean code =  (Boolean) result.get("CODE");
		if(code) {
			questRepository.findById(questId).ifPresent(q->{
				q.setQuestPoint(q.getQuestPoint()+1);
				questRepository.save(q);
			});
		}else {
			questRepository.findById(questId).ifPresent(q->{
				q.setQuestPoint(q.getQuestPoint()-1);
				questRepository.save(q);
			});
		}
	}
	
	public Quest questRegistration(QuestDTO dto, String path, QuestHashTag tag) {
		
		Quest quest = new Quest();
		quest.setQuestTitle(dto.getQuestTitle());
		quest.setQuestPoint(0L);
		quest.setQuestSubject(dto.getQuestSubject());
		quest.setQuestContent(dto.getQuestContent());
		quest.setQuestUploadDate(new Date());
		quest.setQuestActionStart(dto.getQuestActionStart());
		quest.setQuestActionEnd(dto.getQuestActionEnd());
		quest.setQuestStartDate(dto.getQuestStartDate());
		quest.setQuestEndDate(dto.getQuestEndDate());
		quest.setQuestImageRoad(path);
		quest.setQuestImagePath(path);
		quest.setQuestActionTokenAmount(0L);
		quest.setQuestVotingTokenAmount(0L);
		quest.setQuestSign(false);
		quest.setQuestStatus("DAO");
		quest.setQuestHashTag(tag);
		quest.setQuestAnswer(null);
		
		return questRepository.save(quest);
	}
	
	public Page<Quest> findByDate(Pageable pageable, String startDate, String endDate) throws ParseException {

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if ("".equals(startDate) && "".equals(startDate)) {

			Date today = new Date();
			String day = bf.format(today);

			String start = day.substring(0, 10) + " 00:00:00";
			String end = day.substring(0, 10) + " 23:59:00";

			Date first = bf.parse(start);
			Date second = bf.parse(end);
			return questRepository.findAllByQuestUploadDateBetween(pageable, first, second);

		} else if (!"".equals(startDate) && !"".equals(startDate) && startDate.equals(endDate)) {
			String start = startDate.substring(0, 10) + " 00:00:00";
			Date first = f.parse(start);
			Date second = f.parse(start);

			Calendar c = Calendar.getInstance();
			c.setTime(second);
			c.add(Calendar.DATE, 1);
			second = c.getTime();

			return questRepository.findAllByQuestUploadDateBetween(pageable, first, second);

		} else if ("".equals(startDate) && !"".equals(endDate)) {

			Date second = f.parse(endDate);
			return questRepository.findAllByQuestUploadDateLessThan(pageable, second);

		} else if (!"".equals(startDate) && "".equals(endDate)) {
			Date first = f.parse(startDate);
			return questRepository.findAllByQuestUploadDateGreaterThan(pageable, first);
		} else {
			Date first = f.parse(startDate);
			Date second = f.parse(endDate);

			Calendar c = Calendar.getInstance();
			c.setTime(second);
			c.add(Calendar.DATE, 1);
			second = c.getTime();

			return questRepository.findAllByQuestUploadDateBetween(pageable, first, second);
		}
	}
	
	
	
	
	
	
}

