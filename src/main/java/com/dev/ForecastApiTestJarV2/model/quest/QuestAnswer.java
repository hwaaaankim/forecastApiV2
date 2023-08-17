package com.dev.ForecastApiTestJarV2.model.quest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="TB_QUEST_ANSWER")
public class QuestAnswer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ANSWER_ID")
	private Long answerId;
	
	@Column(name="ANSWER_VALUE")
	private String answerValue;
	
	@Column(name="ANSWER_QUEST_ID")
	private Long answerQuestId;
}
