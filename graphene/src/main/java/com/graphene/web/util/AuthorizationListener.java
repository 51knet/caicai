package com.graphene.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;


public class AuthorizationListener implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationListener.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String path = req.getRequestURI();
		String context = req.getContextPath();
		String url = path.substring(context.length(), path.length());
		String permission = null;
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(userInfo != null){
			permission = userInfo.getUser().getIsadmin();
		}
		
		if (url.startsWith("/admin")) { // ok, for now we only protect admin stuff
			if (! isLoggin(req)) { // not logged in
				resp.sendRedirect("/"); // alternative: redirect to a url with flush message...
				return;
			}
		}
		
		if(url.startsWith("/admin/kefu")){
			if(permission == null || !permission.equals("yes")){
				resp.sendRedirect(context); // alternative: redirect to a url ...
				return;
			}
		}
		
		if(url.startsWith("/admin/park") ){
			if(!isPark(req,session)){
				resp.sendRedirect(context);
				return;
			}
			
		}
		
		if(url.startsWith("/admin/allies")){
			if(!isAllies(req, session)){
				resp.sendRedirect(context);
				return;
			}
		}
		
		chain.doFilter(request, response);

	}
	
	private boolean isLoggin(HttpServletRequest req) {
		UserInfo userInfo = (UserInfo) req.getSession().getAttribute(GlobalDefs.SESSION_USER_INFO);

		return req.getSession().getAttribute(GlobalDefs.SESSION_USER_INFO) != null;
	}
	
	private boolean isPark(HttpServletRequest req, HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String park = userInfo.getUser().getMyright();
		return park .equals("park")? true : false;
	}
	
	private boolean isAllies(HttpServletRequest req, HttpSession session){
		UserInfo userInfo = (UserInfo) session.getAttribute(GlobalDefs.SESSION_USER_INFO);
		String allies = userInfo.getUser().getMyright();
		return allies .equals("allies")? true : false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
