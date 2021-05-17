package com.kjh.shorturl.exception;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseException {

	private String message;

	private HttpStatus status;
	
	private int code;
	
	public static ResponseException setExceptionStatus(ShortUrlException exception) {
		return ResponseException.builder()
					.message(exception.getMessage())
					.status(exception.getStatus())
					.code(exception.getCode())
					.build();
	}
}
