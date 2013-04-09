package com.onlymega.dgaisan.html5maker.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Utility for generating and decoding tokens.
 * 
 * @author Dmitri Gaisan
 *
 */
public class TokenUtil {
	private static Base64 base64 = new Base64();
	/**
	 * Encodes bannerId and userId into one single token.
	 * Token is represented as {@code Base64} encoded {@link String}.
	 * Algorithm: <length of bannerid> + <bannerId> + <length of userId> +
	 * <userId>
	 * 
	 * @param bannerId ID of the banner or empty {@link String}
	 * @param userId ID of the user of empty {@link String}
	 * @return {@link String new Token}
	 */
	public static String getnerateToken(String bannerId, String userId) {
		String combined = "";
		String sBannerIdSize = (bannerId == null || bannerId.isEmpty()) ? "00" 
				: String.valueOf(bannerId.length());
		String sUserIdSize = (userId == null || userId.isEmpty()) ? "00" 
				: String.valueOf(userId.length());

		if (sBannerIdSize.length() == 1) {
			sBannerIdSize = "0" + sBannerIdSize;
		}
		if (sUserIdSize.length() == 1) {
			sUserIdSize = "0" + sUserIdSize;
		}

		combined = (sBannerIdSize.equals("00")) ? sBannerIdSize :
					sBannerIdSize + bannerId;
		combined += (sUserIdSize.equals("00")) ? sUserIdSize :
						sUserIdSize + userId;

		if (combined.equals("00") || combined.equals("0000")) {
			return "";
		}
		
		return new String(base64.encode(combined.getBytes()));
	}
	
	public static String extractUserId(String token) {
		if (token == null || token.isEmpty()) {
			return "";
		}

		String decoded = new String(base64.decode(token.getBytes()));
		int userIdOffset = Integer.valueOf(decoded.substring(0, 2)) + 2;
		String userId = decoded.substring(userIdOffset + 2);

		return userId;
	}
	
	public static String extractBannerId(String token) {
		if (token == null || token.isEmpty()) {
			return "";
		}

		String decoded = new String(base64.decode(token.getBytes()));
		int bannerIdOffser = Integer.valueOf(decoded.substring(0, 2));
		String bannerId = decoded.substring(2, bannerIdOffser + 2);

		return bannerId;
	}
}
