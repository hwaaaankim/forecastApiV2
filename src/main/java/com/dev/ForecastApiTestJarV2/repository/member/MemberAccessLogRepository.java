package com.dev.ForecastApiTestJarV2.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ForecastApiTestJarV2.model.member.MemberAccessLog;

@Repository
public interface MemberAccessLogRepository extends JpaRepository<MemberAccessLog ,Long>{

}
