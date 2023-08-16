//package com.dev.ForecastApiTestJarV2.backup;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.dev.ForecastApiTestJarV2.constant.ApiErrorResponse;
//import com.dev.ForecastApiTestJarV2.constant.JwtTokenProvider;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.UnsupportedJwtException;
//import io.jsonwebtoken.security.SignatureException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@RequiredArgsConstructor
//@Slf4j
//@Component
//public class JwtAuthenticationFilterEX extends OncePerRequestFilter {
//
//	// GenericFilterBean 에서 변경
//	private final JwtTokenProvider tokenProvider;
//	public static final String BEARER_PREFIX = "Bearer ";
//	public static final String AUTHORIZATION_HEADER = "Authorization";
//
////	@Override
////	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////			throws IOException, ServletException, java.io.IOException {
//// 	1. Request Header 에서 JWT 토큰 추출
////		String token = resolveToken((HttpServletRequest) request);
//
//// 2. validateToken 으로 토큰 유효성 검사
////	if (token == null) {
////		throw new RuntimeException("TOKEN NULL");
////	} else if (token != null && jwtTokenProvider.validateToken(token)) {
////		throw new RuntimeException("TOKEN INVALID");
////	} else {
////		Authentication authentication = jwtTokenProvider.getAuthentication(token);
////		SecurityContextHolder.getContext().setAuthentication(authentication);
////		filterChain.doFilter(request, response);
////	}
////			if (token != null && jwtTokenProvider.validateToken(token)) {
////				// 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
////				Authentication authentication = jwtTokenProvider.getAuthentication(token);
////				SecurityContextHolder.getContext().setAuthentication(authentication);
////				chain.doFilter(request, response);
////			}else {
////				throw new RuntimeException("이미 가입되어 있는 유저입니다.");
////			}
//
////
////	}
//
//	// Request Header 에서 토큰 정보 추출
//	private String resolveToken(HttpServletRequest request) {
//		String bearerToken = request.getHeader("Authorization");
//		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//			return bearerToken.substring(7);
//		}
//		return null;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, java.io.IOException {
//
//		String token = resolveToken(request);
//		Map<String, Object> result = new HashMap<>();
//        try {
//            if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
//                Authentication authentication = tokenProvider.getAuthentication(token);
//                System.out.println(authentication);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//       
//        } catch(SignatureException e) {
//        	request.setAttribute("exception", ApiErrorResponse.WRONG_TYPE_TOKEN);
//        } catch (SecurityException | MalformedJwtException e) {
//            request.setAttribute("exception", ApiErrorResponse.WRONG_TYPE_TOKEN);
//        } catch (ExpiredJwtException e) {
//            request.setAttribute("exception", ApiErrorResponse.EXPIRED_TOKEN);
//        } catch (UnsupportedJwtException e) {
//            request.setAttribute("exception", ApiErrorResponse.UNSUPPORTED_TOKEN);
//        } catch (IllegalArgumentException e) {
//            request.setAttribute("exception", ApiErrorResponse.UNKNOWN_ERROR);
//        }catch(org.springframework.dao.DataIntegrityViolationException e) {
//        	request.setAttribute("exception", ApiErrorResponse.NULL_ERROR);
//        } catch (Exception e) {
//            log.error("================================================");
//            log.error("JwtFilter - doFilterInternal() 오류발생");
//            log.error("token : {}", token);
//            log.error("Exception Message : {}", e.getMessage());
//            log.error("Exception StackTrace : {");
//            e.printStackTrace();
//            log.error("}");
//            log.error("================================================");
//            request.setAttribute("exception", ApiErrorResponse.UNKNOWN_ERROR);
//        }
//
//        filterChain.doFilter(request, response);
//	}
//}
