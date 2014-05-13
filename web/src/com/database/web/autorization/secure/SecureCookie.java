package com.database.web.autorization.secure;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class SecureCookie {

	public static final String COOKIE_NAME = "wer34tyu46lkjg60fxcnmlk54jhfrt";
	
	public static void setCookie(HttpServletResponse res, String value) {
		Cookie cookie = new Cookie(COOKIE_NAME, value);
		cookie.setMaxAge(60 * 30); // 30 min
		cookie.setSecure(false);
		res.addCookie(cookie);
	}
	
	public static void resetCookie(HttpServletResponse res) {
		Cookie cookie = new Cookie(COOKIE_NAME, "kill_self");
		cookie.setMaxAge(1); // 1 sec
		cookie.setSecure(false);
		res.addCookie(cookie);
	}
	
}
