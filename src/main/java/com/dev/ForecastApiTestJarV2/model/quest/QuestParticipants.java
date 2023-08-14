package com.dev.ForecastApiTestJarV2.model.quest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="TB_QUEST_PARTICIPANTS")
@Entity
public class QuestParticipants {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_PARTICIPANTS_ID")
	private Long questParticipantsId;
	
	
	
}

















