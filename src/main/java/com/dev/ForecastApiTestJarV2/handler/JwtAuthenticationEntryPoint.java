package com.dev.ForecastApiTestJarV2.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.dev.ForecastApiTestJarV2.constant.ApiErrorResponse;

import io.jsonwebtoken.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
	private final HandlerExceptionResolver resolver;
	
	
    public JwtAuthenticationEntryPoint(
    		@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver
    		) {
        this.resolver = resolver;
    }
 
    @Override
    public void commence(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		AuthenticationException authException
    		) throws java.io.IOException {
    	int exception = (int)request.getAttribute("exception");
        if(exception == 200) {
            setResponse(response, ApiErrorResponse.UNKNOWN_ERROR.getMessage());
        }
        //잘못된 타입의 토큰인 경우
        else if(exception == ApiErrorResponse.WRONG_TYPE_TOKEN.getCode()) {
            setResponse(response, ApiErrorResponse.WRONG_TYPE_TOKEN.getMessage());
        }
        //토큰 만료된 경우
        else if(exception == ApiErrorResponse.EXPIRED_TOKEN.getCode()) {
            setResponse(response, ApiErrorResponse.EXPIRED_TOKEN.getMessage());
        }
        //지원되지 않는 토큰인 경우
        else if(exception == ApiErrorResponse.UNSUPPORTED_TOKEN.getCode()) {
            setResponse(response, ApiErrorResponse.UNSUPPORTED_TOKEN.getMessage());
        }
        else if(exception == ApiErrorResponse.NULL_ERROR.getCode()){
        	setResponse(response, ApiErrorResponse.NULL_ERROR.getMessage());
        }
        else {
            setResponse(response, ApiErrorResponse.ACCESS_DENIED.getMessage());
        }
    	resolver.resolveException(request, response, null, authException);
    }
    
    private void setResponse(HttpServletResponse response, String message) throws IOException, java.io.IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", message);
        responseJson.put("code", message);

        response.getWriter().print(responseJson);
    }
}










