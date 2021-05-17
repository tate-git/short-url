package com.kjh.shorturl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kjh.shorturl.constant.ShortUrlStatus;
import com.kjh.shorturl.dto.RequestShortUrl;
import com.kjh.shorturl.entity.ShortUrl;
import com.kjh.shorturl.exception.UrlNotFoundException;
import com.kjh.shorturl.mapper.ShortUrlMapper;
import com.kjh.shorturl.util.ShortUrlUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

	private final ShortUrlMapper shortUrlMapper;
	
	@Value("${short-root-path}")
	private String shortRootPath;
	
	@Transactional(rollbackFor = Exception.class)
	public ShortUrl addShortUrl(RequestShortUrl request) {
		ShortUrl shortUrl = ShortUrl.builder()
								.protocol(request.getProtocol())
								.host(request.getHost())
								.port(request.getPort())
								.pathname(request.getPathname())
								.requestCount(0L)
								.build();
		
		ShortUrl getShortUrl = shortUrlMapper.getShortUrl(shortUrl);
		
		if (ObjectUtils.isEmpty(getShortUrl)) {
			shortUrlMapper.addShortUrl(shortUrl);
			
			shortUrl.createShortingUrl(shortRootPath, ShortUrlUtils.base62Encoder(shortUrl.getShortUrlIdx()));
			shortUrlMapper.modifyShortUrl(shortUrl);
			
			return shortUrl;
		}
		
		return getShortUrl;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public ShortUrl getOriginMappingUrl(String shortingPathname) {
		ShortUrl shortUrl = ShortUrl.builder()
								.shortUrlIdx(ShortUrlUtils.base62Decoder(shortingPathname))
								.build();
		
		ShortUrl getShortUrl = shortUrlMapper.getOriginMappingUrl(shortUrl);
		
		if (ObjectUtils.isEmpty(getShortUrl)) {
			throw new UrlNotFoundException(ShortUrlStatus.NOT_FOUND_SHORT_URL);
		}
		shortUrlMapper.modifyRequestCount(shortUrl);
		
		return getShortUrl;
	}
}
