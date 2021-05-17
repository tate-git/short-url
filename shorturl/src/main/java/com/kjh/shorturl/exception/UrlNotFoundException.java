package com.kjh.shorturl.exception;

import com.kjh.shorturl.constant.ShortUrlStatus;

public class UrlNotFoundException extends ShortUrlException {

	private static final long serialVersionUID = 1L;

	public UrlNotFoundException(ShortUrlStatus shortUrlStatus) {
		super(shortUrlStatus);
	}

}
