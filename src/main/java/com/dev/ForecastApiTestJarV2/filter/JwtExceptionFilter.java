package com.dev.ForecastApiTestJarV2.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.ForecastApiTestJarV2.constant.ApiErrorResponse;

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
	
	@Override
    protected void doFilterInternal(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain filterChain) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		try{
			request.setAttribute("exception", 200);
			filterChain.doFilter(request, response);
        } catch(SignatureException e) {
        	request.setAttribute("exception", ApiErrorResponse.WRONG_TYPE_TOKEN.getCode());
        	filterChain.doFilter(request, response);
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", ApiErrorResponse.WRONG_TYPE_TOKEN.getCode());
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", ApiErrorResponse.EXPIRED_TOKEN.getCode());
            filterChain.doFilter(request, response);
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", ApiErrorResponse.UNSUPPORTED_TOKEN.getCode());
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", ApiErrorResponse.UNKNOWN_ERROR.getCode());
            filterChain.doFilter(request, response);
        }catch(org.springframework.dao.DataIntegrityViolationException e) {
        	request.setAttribute("exception", ApiErrorResponse.NULL_ERROR);
        	filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute("exception", ApiErrorResponse.UNKNOWN_ERROR.getCode());
            filterChain.doFilter(request, response);
        }
    }
}
