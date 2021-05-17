package com.kjh.shorturl.exception;

import com.kjh.shorturl.constant.ShortUrlStatus;

public class UrlInvalidException extends ShortUrlException {

	private static final long serialVersionUID = 1L;

	public UrlInvalidException(ShortUrlStatus shortUrlStatus) {
		super(shortUrlStatus);
	}

}
