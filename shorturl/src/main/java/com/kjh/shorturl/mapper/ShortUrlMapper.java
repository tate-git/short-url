package com.kjh.shorturl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kjh.shorturl.entity.ShortUrl;

@Mapper
public interface ShortUrlMapper {

	ShortUrl getOriginMappingUrl(ShortUrl shortUrl);

	void addShortUrl(ShortUrl shortUrl);

	ShortUrl getShortUrl(ShortUrl shortUrl);

	void modifyShortUrl(ShortUrl shortUrl);

	void modifyRequestCount(ShortUrl shortUrl);

}
