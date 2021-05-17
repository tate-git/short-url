package com.kjh.shorturl.constant;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShortUrlStatus {

	INVALID_SHORT_URL("잘못된 형식의 url입니다.", HttpStatus.BAD_REQUEST),
	NOT_FOUND_SHORT_URL("존재하지않는 url입니다.", HttpStatus.NOT_FOUND);

	private final String message;

	private final HttpStatus status;
}
