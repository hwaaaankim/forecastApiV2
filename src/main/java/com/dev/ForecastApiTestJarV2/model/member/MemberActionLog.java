package com.dev.ForecastApiTestJarV2.model.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TB_MEMBER_ACTION_LOG")
public class MemberActionLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACTION_LOG_ID")
	private Long actionLogId;
	
	// true 인 경우 게시에 찬성
	// false 인 경우 게시에 반대
	@Column(name="ACTION_LOG_SIGN")
	private Boolean actionLogSign;
	
	@Column(name="ACTION_LOG_TEXT")
	private String actionLogText;
	
	@Column(name="ACTION_LOG_DATE")
	private Date actionLogDate;
	
	@Column(name="ACTION_LOG_QUEST_ID")
	private Long actionLogQuestId;
	
	@Column(name="ACTION_LOG_MEMBER_ID")
	private Long actionLogMemberId;
	
	@Column(name="ACTION_LOG_TOKEN_AMOUNT")
	private Long actionLogTokenAmount;
}
