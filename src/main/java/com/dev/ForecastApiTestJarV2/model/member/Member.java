package com.dev.ForecastApiTestJarV2.model.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "TB_ADMINISTRATION")
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
    @Column(name = "ADMINISTRATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ADMINISTRATION_USERNAME", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "ADMINISTRATION_PASSWORD", length = 100)
    private String password;
  
    @Column(name="ADMINISTRATION_DATE")
    private Date administrationDate;

    @Column(name = "ADMINISTRATION_NICKNAME", length = 50)
    private String nickname;

    @JsonIgnore
    @Column(name = "ADMINISTRATION_ACTIVATED")
    private boolean activated;

    @Column(name="ADMINISTRATION_ROLE", length = 50)
	private String roles;
    
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(roles));
        return auth;
	}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

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
}