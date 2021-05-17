package com.kjh.shorturl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kjh.shorturl.dto.RequestShortUrl;
import com.kjh.shorturl.entity.ShortUrl;
import com.kjh.shorturl.mapper.ShortUrlMapper;
import com.kjh.shorturl.service.ShortUrlService;
import com.kjh.shorturl.util.ShortUrlUtils;

@SpringBootTest
public class ShortUrlTests {
	
	@Autowired
	ShortUrlService shortUrlService;
	
	@Test
	@DisplayName("ShortUrl 생성 테스트")
	void createShortUrlTests() {

		// given
		ShortUrl shortUrl = mock(ShortUrl.class);
		Long shortUrlIdx = 1L;
		String protocol = "https://";
		String host = "www.naver.com";
		int port = 443;
		String pathname = "/";
		String expected = "http://localhost/urls/B";
		String actual = "http://localhost/urls/" + ShortUrlUtils.base62Encoder(shortUrlIdx);

		// when
		when(shortUrl.getShortUrlIdx()).thenReturn(shortUrlIdx);
		when(shortUrl.getProtocol()).thenReturn(protocol);
		when(shortUrl.getHost()).thenReturn(host);
		when(shortUrl.getPort()).thenReturn(port);
		when(shortUrl.getPathname()).thenReturn(pathname);
		when(shortUrl.getShortingUrl()).thenReturn(expected);

		// then
		assertNotNull(shortUrl);
		assertEquals(shortUrl.getShortingUrl(), actual);
	}
	
	@Test
	@DisplayName("Shortening 된 URL을 요청받으면 원래 URL로 리다이렉트 테스트")
	void shortingUrlWithOriginUrl() {
		
		// given
		ShortUrlMapper shortUrlMapper = mock(ShortUrlMapper.class);
		
		String shortingPathname = "CJME";
		ShortUrl shortUrl = ShortUrl.builder().shortUrlIdx(ShortUrlUtils.base62Decoder(shortingPathname)).build();
		
		ShortUrl data = ShortUrl.builder()
							.protocol("http://")
							.host("naver.com")
							.port(80)
							.pathname("/")
							.build();
		
		// when
		when(shortUrlMapper.getShortUrl(shortUrl)).thenReturn(data);
		
		// then
		assertEquals("http://", shortUrlMapper.getShortUrl(shortUrl).getProtocol());
		assertEquals("naver.com", shortUrlMapper.getShortUrl(shortUrl).getHost());
		assertEquals(80, shortUrlMapper.getShortUrl(shortUrl).getPort());
		assertEquals("/", shortUrlMapper.getShortUrl(shortUrl).getPathname());
	}
	
	@Test
	@DisplayName("동일한 URL에 대한 요청은 동일한 Shortening Key로 응답 테스트")
	void sameRequestUrlWithSameShortingUrl() {
		
		// given
		RequestShortUrl request = mock(RequestShortUrl.class);

		// when
		when(request.getProtocol()).thenReturn("https://");
		when(request.getHost()).thenReturn("yotube.com");
		when(request.getPort()).thenReturn(443);
		when(request.getPathname()).thenReturn("/");
		ShortUrl shortUrl1 = shortUrlService.addShortUrl(request);
		ShortUrl shortUrl2 = shortUrlService.addShortUrl(request);
		
		// then
		assertEquals(shortUrl1.getShortingUrl(), shortUrl2.getShortingUrl());
	}
}	
