package com.kjh.shorturl.exception;

import org.springframework.http.HttpStatus;

import com.kjh.shorturl.constant.ShortUrlStatus;

public class ShortUrlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	private HttpStatus status;

	private int code;

	public ShortUrlException(ShortUrlStatus shortUrlStatus) {
		super(shortUrlStatus.getMessage());
		this.message = shortUrlStatus.getMessage();
		this.status = shortUrlStatus.getStatus();
		this.code = this.status.value();
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}
}
