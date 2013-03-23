package com.knet51.courses.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.knet51.ccweb.beans.UserInfo;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.courses.controllers.defs.GlobalDefs;

public class SessionListener implements Filter {
	/**
	 * Default constructor.
	 */
	public SessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Object userInfoObj = null;
		User user = new User();
		UserInfo userInfo = new UserInfo();
		HttpSession session = ((HttpServletRequest) request).getSession();
		UserInfo sessionUserInfo = (UserInfo) session
				.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if (sessionUserInfo == null) {
			System.out.println("=== none seesion ===");
			ServletContext context = session.getServletContext().getContext(
					"/ccweb");
			if (context != null) {
				HttpSession spmsSession = (HttpSession) context
						.getAttribute("ccwebSession");
				if (spmsSession != null) {
					userInfoObj = spmsSession
							.getAttribute(GlobalDefs.SESSION_USER_INFO);
					if (userInfoObj == null) {
						session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
					} else {
						try {
							BeanUtils.copyProperties(user, userInfoObj);
							userInfo = new UserInfo(user);
							session.setAttribute(GlobalDefs.SESSION_USER_INFO,
									userInfo);
						} catch (IllegalAccessException e) {
							session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
						} catch (InvocationTargetException e) {
							session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
						}
					}
				} else {
					session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
				}
			} else {
				session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
			}
		} else {
			System.out.println("=== have seesion ===");
			ServletContext context = session.getServletContext().getContext(
					"/ccweb");
			if (context != null) {
				HttpSession spmsSession = (HttpSession) context
						.getAttribute("ccwebSession");
				if (spmsSession != null) {
					userInfoObj = spmsSession
							.getAttribute(GlobalDefs.SESSION_USER_INFO);
					if (userInfoObj == null) {
						session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
					} else {
						try {
							BeanUtils.copyProperties(user, userInfoObj);
							if (user.getId().equals(sessionUserInfo.getId())) {
									
							} else {
								userInfo = new UserInfo(user);
								session.setAttribute(
										GlobalDefs.SESSION_USER_INFO, userInfo);
							}
						} catch (IllegalAccessException e) {
							session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
						} catch (InvocationTargetException e) {
							session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
						}
					}
				} else {
					session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
				}
			} else {
				session.removeAttribute(GlobalDefs.SESSION_USER_INFO);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
