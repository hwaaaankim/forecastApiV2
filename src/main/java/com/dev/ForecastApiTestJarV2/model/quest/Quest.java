package com.dev.ForecastApiTestJarV2.model.quest;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import com.dev.ForecastApiTestJarV2.model.member.Member;
import com.dev.ForecastApiTestJarV2.model.member.MemberActionLog;
import com.dev.ForecastApiTestJarV2.model.member.MemberVotingLog;

import lombok.Data;

@Entity
@Table(name="TB_QUEST")
@Data
public class Quest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_ID")
	private Long questId;
	
	@Column(name="QUEST_TITLE")
	private String questTitle;
	
	@Column(name="QUEST_POINT")
	@Nullable
	private Long questPoint;
	
	@Column(name="QUEST_SUBJECT")
	private String questSubject;
	
	@Column(name="QUEST_CONTENT")
	private String questContent;
	
	@Column(name="QUEST_UPLOAD_DATE")
	private Date questUploadDate;
	
	// Quest 시작일
	@Column(name="QUEST_START_DATE")
	private Date questStartDate;
	
	// Quest 종료일
	@Column(name="QUEST_END_DATE")
	private Date questEndDate;
	
	// Quest 게시 여부 투표 시작일
	@Column(name="QUEST_ACTION_START")
	private Date questActionStart;
	
	// Quest 게시 여부 투표 종료일
	@Column(name="QUEST_ACTION_END")
	private Date questActionEnd;
	
	// Quest 대표 이미지 Load Path 
	@Column(name="QUEST_IMAGE_ROAD")
	private String questImageRoad;
	
	// Quest 대표 이미지 Root Path
	@Column(name="QUEST_IMAGE_PATH")
	private String questImagePath;
	
	@Column(name="QUEST_ACTION_TOKEN_AMOUNT")
	@Nullable
	private Long questActionTokenAmount;
	
	@Column(name="QUEST_VOTING_TOKEN_AMOUNT")
	@Nullable
	private Long questVotingTokenAmount;
	
	// Quest Action 을 통해 결정된 게시 여부
	// true 인 Quest 만 게시 가능
	@Column(name="QUEST_SIGN")
	private Boolean questSign;
	
	// DAO - FAIL or SUCCESS - END
	@Column(name="QUEST_STATUS")
	private String questStatus;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(
			name="QUEST_HASHTAG_REFER_ID", referencedColumnName="HASHTAG_ID"
			)
	private QuestHashTag questHashTag;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(
			name="QUEST_VOTING_MEMBER_ID", referencedColumnName="MEMBER_ID"
			)
	private Member member;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(
			name="QUEST_ANSWER_REFER_ID", referencedColumnName="ANSWER_ID"
			)
	@Nullable
	private QuestAnswer questAnswer;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "answerQuestId"
			)
	private List<QuestAnswer> questAnswers;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "actionLogQuestId"
			)
	private List<MemberActionLog> memberActionLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "votingLogQuestId"
			)
	private List<MemberVotingLog> memberVotingLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "questImagesQuestId"
			)
	private List<QuestImages> questImages;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "manageLogQuestId"
			)
	private List<QuestManageLog> questManageLogs;
}














