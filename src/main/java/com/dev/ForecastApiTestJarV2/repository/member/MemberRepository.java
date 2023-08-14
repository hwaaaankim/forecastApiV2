package com.dev.ForecastApiTestJarV2.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ForecastApiTestJarV2.model.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member ,Long>{

	Optional<Member> findOneByMemberWalletAddress(String walletAddress);
}
