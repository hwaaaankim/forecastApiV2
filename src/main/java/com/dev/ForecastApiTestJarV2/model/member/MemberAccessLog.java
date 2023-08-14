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
@Table(name="TB_MEMBER_ACCESS_LOG")
public class MemberAccessLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACCESS_LOG_ID")
	private Long accessLogId;
	
	@Column(name="ACCESS_LOG_TEXT")
	private String accessLogText;
	
	@Column(name="ACCESS_LOG_DATE")
	private Date accessLogDate;
	
	// true 인 경우 main 페이지
	// false 인 경우 환전 페이지
	@Column(name="ACCESS_LOG_SIGN")
	private Boolean accessLogSign;
	
	@Column(name="ACCESS_LOG_IP")
	private String accessLogIp;
	
	@Column(name="ACCESS_LOG_JWT")
	private String accessLogJwt;
	
	@Column(name="ACCESS_LOG_MEMBER_ID")
	private Long accessLogMemberId;
	
}



















