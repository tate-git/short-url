package com.kjh.shorturl.dto;

import com.kjh.shorturl.entity.ShortUrl;

import lombok.Builder;
import lombok.Getter;

public class ResponseShortUrl {

	@Getter
	@Builder
	public static class Origin {

		private String protocal;
		
		private String host;
		
		private int port;
		
		private String pathname;

		public static Origin getOriginUrl(ShortUrl shortUrl) {
			return Origin.builder()
					.protocal(shortUrl.getProtocol())
					.host(shortUrl.getHost())
					.port(shortUrl.getPort())
					.pathname(shortUrl.getPathname())
					.build();
		}
		
		public String getFullUrl() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(protocal);
			stringBuilder.append(host);
			stringBuilder.append(":");
			stringBuilder.append(port);
			stringBuilder.append(pathname);
			
			return stringBuilder.toString();
		}
	}

	@Getter
	@Builder
	public static class Short {

		private String shortingUrl;

		public static Short getShortUrl(ShortUrl shortUrl) {
			return Short.builder()
					.shortingUrl(shortUrl.getShortingUrl())
					.build();
		}
	}
}
