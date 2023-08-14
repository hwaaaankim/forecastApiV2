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
@Table(name="TB_QUEST_IMAGES")
public class QuestImages {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_IMAGES_ID")
	private Long questImagesId;
	
	@Column(name="QUEST_IMAGES_PATH")
	private String questImagesPath;
	
	@Column(name="QUEST_IMAGES_ROAD")
	private String questImagesRoad;
	
	@Column(name="QUEST_IMAGES_NAME")
	private String questImagesName;
	
	@Column(name="QUEST_IMAGES_QUEST_ID")
	private Long questImagesQuestId;
}
