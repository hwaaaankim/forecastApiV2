package com.dev.ForecastApiTestJarV2.model.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="TB_MEMBER_TRANSFERING_LOG")
@Data
@Entity
public class MemberTransferingLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRANSFERING_LOG_ID")
	private Long transferingLogId;
	
	@Column(name="TRANSFERING_LOG_SUBJECT")
	private String transferingLogSubject;
	
	@Column(name="TRANSFERING_LOG_CONTENT")
	private String transferingLogContent;
	
	@Column(name="TRANSFERING_LOG_DATE")
	private Date transferingLogDate;
	
	@Column(name="TRANSFERING_LOG_TOKEN_AMOUNT")
	private Long transferingLogTokenAmount;
		
	// true 인 경우 A 토큰 to B
	// false 인 경우 B 토큰 to A
	@Column(name="TRANSFERING_LOG_SIGN")
	private Boolean transferingLogSign;
	
	@Column(name="TRANSFERING_LOG_MEMBER_ID")
	private Long transferingLogMemberId;
	
}
