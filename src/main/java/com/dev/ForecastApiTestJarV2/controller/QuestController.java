package com.dev.ForecastApiTestJarV2.controller;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ForecastApiTestJarV2.model.quest.Quest;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestHashTagRepository;
import com.dev.ForecastApiTestJarV2.repository.quest.QuestRepository;
import com.dev.ForecastApiTestJarV2.service.quest.QuestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/quest")
@Slf4j
@RequiredArgsConstructor
public class QuestController {

	private final QuestService questService;
	private final QuestRepository questRepository;
	private final QuestHashTagRepository questHashTagRepository;
	
	@GetMapping( value = "/questSelect")
	public @ResponseBody Object questRegistration(
			@PageableDefault(size = 10) Pageable pageable,
			@RequestParam(required = false, defaultValue = "NONE") String searchType, 
			// SUBJECT, CONTENT, TITLE :  단어가 포함된 Quest 조회
			// STATUS : DAO / FAIL / SUCCESS / END 상태의 Quest들 조회
			// HASHTAG : 해당 태그의 Quest 조회
			// UPLOAD : Start date 와 End date 사이에 Upload된 Quest 조회 
			// ACTION : 아직 게시여부가 정해지지 않은 - DAO - Quest들을 End Date가 가까운 순으로 조회
			// VOTING : Voting중인 Quest들을 End Date가 가까운 순으로 조회
			// AMOUNT : Voting 진행중인 Quest중 - SUCCESS - Voting에 걸린 Point가 높은 Quest 순으로 조회
			@RequestParam(required = false) String searchWord,
			@RequestParam(required = false) Long hashTagId, 
			@RequestParam(required = false) String startDate, 
			@RequestParam(required = false) String endDate
			) throws ParseException{
		
		Page<Quest> quests = null;
		
		if(searchType.equals("SUBJECT")) {
			quests = questRepository.findAllByQuestSubjectContaining(pageable, searchWord);
		}else if(searchType.equals("CONTENT")) {
			quests = questRepository.findAllByQuestContentContaining(pageable, searchWord);
		}else if(searchType.equals("TITLE")) {
			quests = questRepository.findAllByQuestTitleContaining(pageable, searchWord);
		}else if(searchType.equals("STATUS")) {
			quests = questRepository.findAllByQuestStatus(pageable, searchWord);
		}else if(searchType.equals("HASHTAG")) {
			quests = questRepository.findAllByQuestHashTag(pageable, questHashTagRepository.findById(hashTagId).get());
		}else if(searchType.equals("UPLOAD")) {
			quests = questService.findByDate(pageable, startDate, endDate);
		}else if(searchType.equals("ACTION")) {
			quests = questRepository.findAllByQuestStatusOrderByQuestActionEndDesc(pageable,"DAO");
		}else if(searchType.equals("VOTING")) {
			quests = questRepository.findAllByQuestStatusOrderByQuestActionEndDesc(pageable,"SUCCESS");
		}else if(searchType.equals("AMOUNT")) {
			quests = questRepository.findAllByQuestStatusOrderByQuestVotingTokenAmountDesc(pageable,"SUCCESS");
		}else {
			quests = questRepository.findAll(pageable);
		}
		
		int startPage = Math.max(1, quests.getPageable().getPageNumber() - 4);
		int endPage = Math.min(quests.getTotalPages(), quests.getPageable().getPageNumber() + 4);
		HashMap<String, Object> quest = new HashMap<String, Object>();
		quest.put("quests", quests);
		quest.put("startPage", startPage);
		quest.put("endPage", endPage);
		quest.put("totalPage", quests.getPageable());
		quest.put("totalElements", quests.getTotalElements());
		
		return quest;
	}
	
	@GetMapping( value = "/questSelectDetail/{id}")
	public @ResponseBody Object questRegistration(
			@PathVariable Long id
			) {
		
		return questRepository.findById(id).get();
	}
}





















