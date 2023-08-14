package com.dev.ForecastApiTestJarV2.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.ForecastApiTestJarV2.constant.ApiErrorResponse;
import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter{
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final JwtTokenProvider tokenProvider;
	
	@Override
    protected void doFilterInternal(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain filterChain) throws ServletException, IOException {
		String jwt = resolveToken(request);
		String requestURI = request.getRequestURI();
		System.out.println("doFilterInternal");
		response.setCharacterEncoding("utf-8");
        try{
        	if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Authentication authentication = tokenProvider.getAuthentication(jwt);
				System.out.println(authentication.getCredentials());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
			} 
        	request.setAttribute("exception", 200);
            filterChain.doFilter(request, response);
        } catch(SignatureException e) {
        	request.setAttribute("exception", ApiErrorResponse.WRONG_TYPE_TOKEN.getCode());
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", ApiErrorResponse.WRONG_TYPE_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", ApiErrorResponse.EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", ApiErrorResponse.UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", ApiErrorResponse.UNKNOWN_ERROR.getCode());
        }catch(org.springframework.dao.DataIntegrityViolationException e) {
        	request.setAttribute("exception", ApiErrorResponse.NULL_ERROR);
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute("exception", ApiErrorResponse.UNKNOWN_ERROR.getCode());
        }

        filterChain.doFilter(request, response);

    }
	
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.split(" ")[1].trim();
		}

		return null;
	}
	
}
