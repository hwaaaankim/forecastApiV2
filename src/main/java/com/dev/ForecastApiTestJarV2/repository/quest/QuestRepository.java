package com.dev.ForecastApiTestJarV2.repository.quest;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ForecastApiTestJarV2.model.quest.Quest;
import com.dev.ForecastApiTestJarV2.model.quest.QuestHashTag;

public interface QuestRepository extends JpaRepository<Quest, Long>{

	Page<Quest> findAllByQuestSubjectContaining(Pageable pageable, String subject);
	Page<Quest> findAllByQuestContentContaining(Pageable pageable, String content);
	Page<Quest> findAllByQuestTitleContaining(Pageable pageable, String title);
	Page<Quest> findAllByQuestStatus(Pageable pagealbe, String status);
	Page<Quest> findAllByQuestHashTag(Pageable pagealbe, QuestHashTag tag);
	Page<Quest> findAllByQuestStatusOrderByQuestActionEndDesc(Pageable pagealbe, String status);
	Page<Quest> findAllByQuestStatusOrderByQuestEndDateDesc(Pageable pagealbe, String status);
	Page<Quest> findAllByQuestStatusOrderByQuestVotingTokenAmountDesc(Pageable pagealbe, String status);
	
	Page<Quest> findAllByQuestUploadDateLessThan(Pageable pageable,Date date);
	Page<Quest> findAllByQuestUploadDateGreaterThan(Pageable pageable,Date date);
	Page<Quest> findAllByQuestUploadDateBetween(Pageable pageable, Date startDate, Date endDate);
}
