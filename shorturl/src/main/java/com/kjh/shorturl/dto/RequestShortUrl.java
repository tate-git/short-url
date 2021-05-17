package com.kjh.shorturl.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class RequestShortUrl {

	@Pattern(regexp = "(http:\\/\\/|https:\\/\\/)")
	private String protocol;

	@NotBlank
	private String host;

	@Min(0)
	@Max(65535)
	private int port;

	private String pathname;

}
