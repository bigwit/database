package com.database.web.autorization.secure;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.database.web.autorization.GridData;

public class CookieFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain fc) throws ServletException,
			IOException {
		if (req.getCookies() != null) {
			for (Cookie c : req.getCookies()) {
				if (c.getName().equals(SecureCookie.COOKIE_NAME)) {
					req.setAttribute("USER",
							GridData.getInstance().getUser(c.getValue()));
					break;
				}
			}
		}
		fc.doFilter(req, res);
	}
}
