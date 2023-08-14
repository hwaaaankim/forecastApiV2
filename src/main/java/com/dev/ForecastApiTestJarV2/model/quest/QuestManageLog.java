package com.dev.ForecastApiTestJarV2.model.quest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.Data;

@Entity
@Data
@Table(name="TB_QUEST_MANAGE_LOG")
public class QuestManageLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MANAGE_LOG_ID")
	private Long manageLogId;
	
	@Column(name="MANAGE_LOG_SUBJECT")
	@Nullable
	private String manageLogSubject;
	
	@Column(name="MANAGE_LOG_CONTENT")
	@Nullable
	private String manageLogContent;
	
	@Column(name="MANAGE_LOG_DATE")
	private Date manageLogDate;
	
	@Column(name="MANAGE_LOG_QUEST_ID")
	private Long manageLogQuestId;
	
	@Column(name="MANAGE_LOG_MEMBER_ID")
	private Long manageLogMemberId;
	
}
