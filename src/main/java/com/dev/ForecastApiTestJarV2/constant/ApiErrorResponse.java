package com.dev.ForecastApiTestJarV2.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiErrorResponse {

	INDEX_NOT_FOUND(1001, "인덱스가 존재하지 않습니다."),
    BOARD_NOT_FOUND(1002, "게시글을 찾을 수 없습니다."),
    UNKNOWN_ERROR(1003, "토큰이 존재하지 않습니다."),
    WRONG_TYPE_TOKEN(1004, "잘못된 토큰입니다."),
    EXPIRED_TOKEN(1005, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(1006, "변조된 토큰입니다."),
    ACCESS_DENIED(1007, "권한이 없습니다."),
	METHOD_ERROR(1008, "잘못된 http method 입니다."),
	USERNAME_ERROR(1009, "잘못된 USERNAME 입니다."),
	PASSWORD_ERROR(1010, "잘못된 PASSWORD 입니다."),
	EXPIRED_ERROR(1011, "계정이 접속 불가능한 상태 입니다."),
	NULL_ERROR(1012, "필요한 정보를 모두 입력 해 주세요."),
	NOT_SUPPORTED(1013, "정확한 Http Method를 이용 해 주세요."),
	REQUEST_EXCEPTION(1014, "정확한 Request Type을 이용 해 주세요."),
	ALREADY_USER(1015, "이미 가입한 wallet address 입니다."),
	NOTFOUND_ERROR(1016, "존재하지 않는 request url 입니다."),
	DATABASE_ERROR(1017, "database error 발생."),
	SERVER_ERROR(1018, "server error 발생.");
	
	private final int code;
	private final String message;
}
