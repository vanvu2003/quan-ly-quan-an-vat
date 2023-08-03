package edu.poly.helpdb;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static Cookie set(String name, String value, int hour, HttpServletResponse resp) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(hour * 60 * 60);
		cookie.setPath("/");
		resp.addCookie(cookie);
		
		return cookie;
	}
	
	public static String get(String name, HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie.getValue().toString();
				}
			}
		}
		return null;
	}
}
