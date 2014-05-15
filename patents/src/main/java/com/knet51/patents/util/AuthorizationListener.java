package com.knet51.patents.util;

import java.io.IOException;
import java.util.StringTokenizer;

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

import com.knet51.patents.beans.UserInfo;
import com.knet51.patents.controllers.common.defs.GlobalDefs;



/**
 * Servlet Filter implementation class AuthorizationListener
 */
public class AuthorizationListener implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationListener.class);
	
    /**
     * Default constructor. 
     */
    public AuthorizationListener() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = ((HttpServletRequest)request);
		HttpServletResponse resp = ((HttpServletResponse)response); 
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
		
		if(url.startsWith("/admin/investcompany") && isInvestCompany(req)){
			resp.sendRedirect(context);
			return;
		}
		
		if(url.startsWith("/admin/incubator") && isIncubator(req)){
			resp.sendRedirect(context);
			return;
		}
		
//		StringTokenizer st = new StringTokenizer(url, "/");
//		String[] a = new String[2]; 
//		for (int i = 0; i < st.countTokens(); i++) {
//			System.out.println(a[i]=(String) st.nextElement());
//		}
//		System.out.println(a[0]+"---"+a[1]);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean isLoggin(HttpServletRequest req) {
		UserInfo userInfo = (UserInfo) req.getSession().getAttribute(GlobalDefs.SESSION_USER_INFO);
		if(userInfo!=null && userInfo.getUser().getForbidden()!=null){
			req.getSession().invalidate();
		}
		return req.getSession().getAttribute(GlobalDefs.SESSION_USER_INFO) != null;
	}
	
	private boolean isInvestCompany(HttpServletRequest req){
		String investCompany = (String) req.getSession().getAttribute("investcompany");
		return investCompany == null? true : false;
	}
	
	private boolean isIncubator(HttpServletRequest req){
		String incubator = (String) req.getSession().getAttribute("incubator");
		return incubator == null? true : false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
