package com.kjh.shorturl.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kjh.shorturl.constant.ShortUrlStatus;
import com.kjh.shorturl.dto.RequestShortUrl;
import com.kjh.shorturl.dto.ResponseShortUrl;
import com.kjh.shorturl.entity.ShortUrl;
import com.kjh.shorturl.exception.ShortUrlException;
import com.kjh.shorturl.exception.ResponseException;
import com.kjh.shorturl.exception.UrlInvalidException;
import com.kjh.shorturl.service.ShortUrlService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/urls")
@RequiredArgsConstructor
public class ShortUrlController {

	private final ShortUrlService shortUrlService;

	@PostMapping
	public ResponseEntity<ResponseShortUrl.Short> addShortUrl(@RequestBody @Valid RequestShortUrl request,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new UrlInvalidException(ShortUrlStatus.INVALID_SHORT_URL);
		}

		ShortUrl shortUrl = shortUrlService.addShortUrl(request);
		ResponseShortUrl.Short response = ResponseShortUrl.Short.getShortUrl(shortUrl);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{shortingPathname}")
	public void originMappingUrl(@PathVariable String shortingPathname, HttpServletResponse httpServletResponse)
			throws Exception {
		ShortUrl shortUrl = shortUrlService.getOriginMappingUrl(shortingPathname);
		ResponseShortUrl.Origin response = ResponseShortUrl.Origin.getOriginUrl(shortUrl);

		httpServletResponse.sendRedirect(response.getFullUrl());
	}

	@ExceptionHandler(ShortUrlException.class)
	public ResponseEntity<ResponseException> shortUrlException(ShortUrlException exception) {
		ResponseException responseException = ResponseException.setExceptionStatus(exception);

		return new ResponseEntity<>(responseException, responseException.getStatus());
	}
}
