package com.kjh.shorturl.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortUrl {

	private Long shortUrlIdx;

	private String protocol;

	private String host;

	private int port;

	private String pathname;

	private String shortingUrl;

	private long requestCount;

	private LocalDateTime createDate;

	public ShortUrl() {

	}

	@Builder
	public ShortUrl(Long shortUrlIdx, String protocol, String host, int port, String pathname, long requestCount) {
		this.shortUrlIdx = shortUrlIdx;
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.pathname = pathname;
		this.requestCount = requestCount;
	}

	public void createShortingUrl(String shortRootPath, String shortingPathname) {
		this.shortingUrl = shortRootPath + shortingPathname;
	}

}
