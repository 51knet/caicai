package com.knet51.ccweb.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knet51.ccweb.controllers.defs.GlobalDefs;

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
		String path = req.getRequestURI();
		String context = req.getContextPath();
		//logger.info("+++++++++++++"+path);
		//logger.info("-------------"+context);
		String url = path.substring(context.length(), path.length());
		logger.debug("->"+url);
		if (url.startsWith("/admin")) { // ok, for now we only protect admin stuff
			if (! isLoggin(req)) { // not logged in
				resp.sendRedirect(context); // alternative: redirect to a url with flush message...
				return;
			}
		}else if(url.startsWith("/teacher")){
			req.getRequestDispatcher(url).forward(req, resp);
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	private boolean isLoggin(HttpServletRequest req) {
		return req.getSession().getAttribute(GlobalDefs.SESSION_USER_INFO) != null;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
