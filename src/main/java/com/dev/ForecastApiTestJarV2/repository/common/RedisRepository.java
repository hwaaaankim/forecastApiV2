package com.dev.ForecastApiTestJarV2.repository.common;

import org.springframework.data.repository.CrudRepository;

import com.dev.ForecastApiTestJarV2.model.member.MemberJWTRedis;

public interface RedisRepository extends CrudRepository<MemberJWTRedis, String> {
	
}
