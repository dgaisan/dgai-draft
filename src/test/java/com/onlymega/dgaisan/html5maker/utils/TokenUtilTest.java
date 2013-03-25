package com.onlymega.dgaisan.html5maker.utils;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokenUtilTest {

	@Test
	public void testTokenGenerationWithNoBannerNoUser() {
		String expected = "";
		String actual = TokenUtil.getnerateToken(null, "");

		assertTrue(actual.equals(expected));
	}

	@Test
	public void testTokenGenerationWithNoBanner() {
		String userId = "315";
		String actual = TokenUtil.getnerateToken(null, userId);
		Base64 b = new Base64();
		String expected = new String(b.encode("0003315".getBytes()));

		assertTrue(actual.equals(expected));
	}

	@Test
	public void testFullTokenGeneration() {
		String userId = "1827";
		String bannerId = "3";
		String actual = TokenUtil.getnerateToken(bannerId, userId);
		Base64 b = new Base64();
		String expected = new String(b.encode("013041827".getBytes()));

		assertTrue(actual.equals(expected));
	}

	@Test
	public void testExtractUserIdNoBanner() {
		String userId = "1827";
		String bannerId = "";
		String token = TokenUtil.getnerateToken(bannerId, userId);
		String actual = TokenUtil.extractUserId(token);

		assertTrue(actual.equals(userId));
	}

	@Test
	public void testExtractUserIdWhenBannerProvidedToo() {
		String userId = "6";
		String bannerId = "292839102";
		String token = TokenUtil.getnerateToken(bannerId, userId);
		String actual = TokenUtil.extractUserId(token);

		assertTrue(actual.equals(userId));
	}

	@Test
	public void testExtractBannerId() {
		String userId = "6281937293";
		String bannerId = "193802";
		String token = TokenUtil.getnerateToken(bannerId, userId);
		String actual = TokenUtil.extractBannerId(token);

		assertTrue(actual.equals(bannerId));
	}

	@Test
	public void testExtractEverything() {
		String userId = "18276312";
		String bannerId = "1987";
		String token = TokenUtil.getnerateToken(bannerId, userId);
		String actualBannerId = TokenUtil.extractBannerId(token);
		String actualUserId = TokenUtil.extractUserId(token); 

		assertTrue(actualBannerId.equals(bannerId));
		assertTrue(actualUserId.equals(userId));
	}
}
