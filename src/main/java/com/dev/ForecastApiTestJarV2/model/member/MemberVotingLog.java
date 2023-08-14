package com.dev.ForecastApiTestJarV2.model.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TB_MEMBER_VOTING_LOG")
@Data
public class MemberVotingLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="VOTING_LOG_ID")
	private Long votingLogId;
	
	@Column(name="VOTING_LOG_DATE")
	private Date votingLogDate;
	
	@Column(name="VOTING_LOG_TEXT")
	private String votingLogText;
	
	@Column(name="VOTING_LOG_MEMBER_ID")
	private Long votingLogMemberId;
	
	@Column(name="VOTING_LOG_QUEST_ID")
	private Long votingLogQuestId;
	
	@Column(name="VOTING_LOG_TOKEN_AMOUNT")
	private Long votingLogTokenAmount;
	
	// true 인 경우 찬성
	// false 인 경우 반대
	@Column(name="VOTING_LOG_SIGN")
	private Boolean votingLogSign;
}











