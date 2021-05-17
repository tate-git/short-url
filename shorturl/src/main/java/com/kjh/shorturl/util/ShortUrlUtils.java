package com.kjh.shorturl.util;

public class ShortUrlUtils {

	private static final int BASE62_NUMBER = 62;

	private static final String BASE62_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	public static String base62Encoder(long number) {
		StringBuilder stringBuilder = new StringBuilder();

		char[] base62Chars = BASE62_STRING.toCharArray();

		while (number > 0) {
			int index = (int) (number % BASE62_NUMBER);
			stringBuilder.append(base62Chars[index]);
			number /= BASE62_NUMBER;
		}

		return stringBuilder.toString();
	}

	public static long base62Decoder(String shortingPathname) {
		long result = 0;
		long power = 1;
		int length = shortingPathname.length();

		for (int i = 0; i < length; i++) {
			int digit = BASE62_STRING.indexOf(shortingPathname.charAt(i));
			result += digit * power;
			power *= BASE62_NUMBER;
		}

		return result;
	}
}
