package com.dev.ForecastApiTestJarV2.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class QuestDTO {

	private String questTitle;
	private String questSubject;
	private String questContent;
	private Date questStartDate;
	private Date questEndDate;
	private Date questActionStart;
	private Date questActionEnd;
	private Long questHashTag;
	private List<String> questAnswer;
	
}
