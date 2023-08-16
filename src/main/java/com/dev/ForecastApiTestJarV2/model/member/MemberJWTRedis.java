package com.dev.ForecastApiTestJarV2.model.member;

import java.util.Date;

import lombok.Data;

@Data
//@RedisHash(value = "jwtInfo", timeToLive = 30)
public class MemberJWTRedis {

	private String walletAddress;
	private String refreshJwt;
	private String accessJwt;
	private Date date;
}
