package com.dev.ForecastApiTestJarV2.model.member;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dev.ForecastApiTestJarV2.model.quest.QuestAnswer;
import com.dev.ForecastApiTestJarV2.model.quest.QuestHashTag;

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
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(
			name="VOTING_LOG_REFER_ID", referencedColumnName="ANSWER_ID"
			)
	private QuestAnswer votingLogAnswer;
}











