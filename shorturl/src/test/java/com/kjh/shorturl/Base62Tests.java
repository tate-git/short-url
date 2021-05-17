package com.kjh.shorturl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kjh.shorturl.util.ShortUrlUtils;

public class Base62Tests {

	@Test
	@DisplayName("Base 62 인코딩 테스트")
	void encodingTest() {
		String expected1 = "B";
		String expected2 = "AB";
		String expected3 = "CJME";

		String actual1 = ShortUrlUtils.base62Encoder(1L);
		String actual2 = ShortUrlUtils.base62Encoder(62L);
		String actual3 = ShortUrlUtils.base62Encoder(1000000L);

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
	}

	@Test
	@DisplayName("Base 62 디코딩 테스트")
	void decodingTest() {
		Long expected1 = 1L;
		Long expected2 = 62L;
		Long expected3 = 1000000L;

		Long actual1 = ShortUrlUtils.base62Decoder("B");
		Long actual2 = ShortUrlUtils.base62Decoder("AB");
		Long actual3 = ShortUrlUtils.base62Decoder("CJME");

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
		assertEquals(expected3, actual3);
	}
}
