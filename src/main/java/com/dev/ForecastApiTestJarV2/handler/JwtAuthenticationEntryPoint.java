package com.dev.ForecastApiTestJarV2.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.dev.ForecastApiTestJarV2.constant.ApiErrorResponse;

import io.jsonwebtoken.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
//	private final HandlerExceptionResolver resolver;
	
//	
//    public JwtAuthenticationEntryPoint(
//    		@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver
//    		) {
//        this.resolver = resolver;
//    }
// 
    @Override
    public void commence(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		AuthenticationException authException
    		) throws java.io.IOException {
    	System.out.println("commence");
    	int exception = (int)request.getAttribute("exception");
    	int nullTokenSign = (int)request.getAttribute("nullTokenSign");
    	int logoutUser = (int)request.getAttribute("logoutUser");
    	System.out.println(logoutUser);
        if(exception == 200) {
            setResponse(response, ApiErrorResponse.UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception == ApiErrorResponse.WRONG_TYPE_TOKEN.getCode() 
        		&& nullTokenSign == 1 ) {
            setResponse(response, ApiErrorResponse.WRONG_TYPE_TOKEN);
        }
        else if(exception == ApiErrorResponse.WRONG_TYPE_TOKEN.getCode() 
        		&& nullTokenSign == 0) {
            setResponse(response, ApiErrorResponse.UNKNOWN_ERROR);
        }
        //토큰 만료된 경우
        else if(exception == ApiErrorResponse.EXPIRED_TOKEN.getCode() || logoutUser == 1) {
            setResponse(response, ApiErrorResponse.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception == ApiErrorResponse.UNSUPPORTED_TOKEN.getCode()) {
            setResponse(response, ApiErrorResponse.UNSUPPORTED_TOKEN);
        }
        else if(exception == ApiErrorResponse.NULL_ERROR.getCode()){
        	setResponse(response, ApiErrorResponse.NULL_ERROR);
        }
        else {
            setResponse(response, ApiErrorResponse.ACCESS_DENIED);
        }
//    	resolver.resolveException(request, response, null, authException);
    }
    
    private void setResponse(HttpServletResponse response, ApiErrorResponse errorResponse) throws IOException, java.io.IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", errorResponse.getMessage());
        responseJson.put("code", errorResponse.getCode());

        response.getWriter().print(responseJson);
    }
}










