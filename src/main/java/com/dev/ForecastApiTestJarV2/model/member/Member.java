package com.dev.ForecastApiTestJarV2.model.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.ForecastApiTestJarV2.model.quest.QuestManageLog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "TB_MEMBER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2023L;

	@JsonIgnore
    @Id 
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "MEMBER_WALLET_ADDRESS", length = 50, unique = true)
    private String memberWalletAddress;
    
    @Column(name = "MEMBER_WALLET_ID", length = 50, unique = true)
    private String memberWalletId;
    
//    @JsonIgnore
//    @Column(name = "MEMBER_PASSWORD", length = 100)
//    private String memberPassword;
  
    @Column(name="MEMBER_JOIN_DATE")
    private Date memberJoinDate;
    
    @Column(name="MEMBER_EMAIL", unique = true)
    private String memberEmail;

    @Column(name = "MEMBER_NICKNAME", length = 50)
    private String memberNickname;

    @JsonIgnore
    @Column(name = "MEMBER_ACTIVATED")
    private boolean memberActivated;

    @Column(name="MEMBER_ROLE", length = 50)
	private String memberRole;
    
    @OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "accessLogMemberId"
			)
	private List<MemberAccessLog> memberAccessLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "transferingLogMemberId"
			)
	private List<MemberTransferingLog> memberTransferingLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "votingLogMemberId"
			)
	private List<MemberVotingLog> memberVotingLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "actionLogMemberId"
			)
	private List<MemberActionLog> memberActionLogs;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			mappedBy = "manageLogMemberId"
			)
	private List<QuestManageLog> questManageLog;
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(memberRole));
        return auth;
	}

    @Override
    public String getUsername() {
        return memberWalletAddress;
    }

//    @Override
//    public String getPassword() {
//        return memberPassword;
//    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}