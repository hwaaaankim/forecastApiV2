package com.dev.ForecastApiTestJarV2.dto;

import lombok.Data;

@Data
public class MemberLoginRequestDTO {

	private String username;
	private String password;
	private String nickname;
	private String roles;
}
