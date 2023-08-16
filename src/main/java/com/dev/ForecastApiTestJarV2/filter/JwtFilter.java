package com.dev.ForecastApiTestJarV2.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.filter.GenericFilterBean;

import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;

import io.jsonwebtoken.MalformedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilterBean {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final JwtTokenProvider tokenProvider;
	private final RedisTemplate<String, String> redisTemplate;

	// 실제 필터링 로직
	// 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
	@Override
	public void doFilter(
			ServletRequest servletRequest, 
			ServletResponse servletResponse, 
			FilterChain filterChain)
			throws IOException, ServletException, java.io.IOException, HttpRequestMethodNotSupportedException{
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String jwt = resolveToken(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();
		
		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			
			String key = tokenProvider.getAuthentication(jwt).getName();
			
//			System.out.println("doFilter if" + key);
			try {
				String storedToken = redisTemplate.opsForValue().get(key);
//		            **로그인 여부 체크**
	            if(redisTemplate.hasKey(key) && storedToken != null) {
//	            	System.out.println("doFilter if");
	            	Authentication authentication = tokenProvider.getAuthentication(jwt);
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                servletRequest.setAttribute("logoutUser", 0);
	                servletRequest.setAttribute("exception", 200);
	                log.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
	            }else {
	            	servletRequest.setAttribute("logoutUser", 1);
	            	servletRequest.setAttribute("exception", 200);
	            }
			}catch(NullPointerException e) {
				servletRequest.setAttribute("exception", 200);
				servletRequest.setAttribute("logoutUser", 1);
			}
			
			servletRequest.setAttribute("nullTokenSign", 1);
		} else {
			log.info("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
			servletRequest.setAttribute("logoutUser", 0);
			servletRequest.setAttribute("nullTokenSign", 0);
			throw new MalformedJwtException("유효한 JWT 토큰이 없습니다");
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
		
	}

	// Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.split(" ")[1].trim();
		}

		return null;
	}
}
