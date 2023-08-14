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
@Table(name="TB_QUEST_HASHTAG")
public class QuestHashTag {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HASHTAG_ID")
	private Long hashtagId;
	
	@Column(name="HASHTAG_NAME")
	private String hashtagName;
	
	@Column(name="HASHTAG_QUEST_ID")
	private Long hashtagQuestId;
}
