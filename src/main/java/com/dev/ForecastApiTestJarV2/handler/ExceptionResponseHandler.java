package com.dev.ForecastApiTestJarV2.handler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dev.ForecastApiTestJarV2.constant.ApiErrorResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class ExceptionResponseHandler {

	Map<String, Object> result = new HashMap<>();
	
//	@ExceptionHandler(SignatureException.class)
//    public ResponseEntity<Object> handleSignatureException() {
//		
//		result.put("status", ApiErrorResponse.UNSUPPORTED_TOKEN.getCode());
//	    result.put("message", ApiErrorResponse.UNSUPPORTED_TOKEN.getMessage());
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
//    }
 
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Object> handleMalformedJwtException() {
    	
    	result.put("status", ApiErrorResponse.WRONG_TYPE_TOKEN.getCode());
	    result.put("message", ApiErrorResponse.WRONG_TYPE_TOKEN.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
 
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException() {
    	
    	result.put("status", ApiErrorResponse.EXPIRED_TOKEN.getCode());
	    result.put("message", ApiErrorResponse.EXPIRED_TOKEN.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException() {
    	
    	result.put("status", ApiErrorResponse.USERNAME_ERROR.getCode());
	    result.put("message", ApiErrorResponse.USERNAME_ERROR.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlerIllegalArgumentException(){
    	
    	result.put("status", ApiErrorResponse.UNKNOWN_ERROR.getCode());
	    result.put("message", ApiErrorResponse.UNKNOWN_ERROR.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handlerSQLIntegrityConstraintViolationException(){
    	
    	result.put("status", ApiErrorResponse.NULL_ERROR.getCode());
	    result.put("message", ApiErrorResponse.NULL_ERROR.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handlerBadCredentialsException(){
    	
	  	result.put("status", ApiErrorResponse.PASSWORD_ERROR.getCode());
	    result.put("message", ApiErrorResponse.PASSWORD_ERROR.getMessage());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	result.put("status", ApiErrorResponse.NOT_SUPPORTED.getCode());
	    result.put("message", ApiErrorResponse.NOT_SUPPORTED.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    	result.put("status", ApiErrorResponse.REQUEST_EXCEPTION.getCode());
	    result.put("message", ApiErrorResponse.REQUEST_EXCEPTION.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
