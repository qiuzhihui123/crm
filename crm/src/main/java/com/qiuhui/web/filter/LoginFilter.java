package com.qiuhui.web.filter;


import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qiuhui.entity.Staff;

public class LoginFilter extends AbstractFilter{
	List<String> uriList = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String validate = filterConfig.getInitParameter("validate");
		String[] strs = validate.split(",");
		 uriList = Arrays.asList(strs);
		
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		if(validate(uri)){
			HttpSession session = req.getSession();
			Staff staff  = (Staff) session.getAttribute("staff");
			
			if(staff != null){
				chain.doFilter(req, resp);
			} else {
				uri = getUriWithParam(uri,req);//TODO  uri!!
				resp.sendRedirect("/login?callback="+uri);
			}
			
		} else{
			chain.doFilter(req, resp);
		}
	}

	private String getUriWithParam(String uri, HttpServletRequest req) {
		Map<String,String[]> params = req.getParameterMap();
		Set<String> keys = params.keySet();
		Iterator<String> it = keys.iterator();
		if(it.hasNext()) {
			uri += "?";
			while(it.hasNext()) {
				String key = it.next();
				String[] values = params.get(key);
				for(String value : values) {
					String param = key + "=" + value +"&"; 
					uri += param;
				}
			}
			uri = uri.substring(0, uri.length()-1);
		}
		return uri;
	}

	private boolean validate(String uri) {
		if(uriList == null){
			return false;
		}
		
		for(String str : uriList){
			if(uri.startsWith(str)){
				return true;
			}
		}
		return false;
	}

}
