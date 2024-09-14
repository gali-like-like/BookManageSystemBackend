package com.sky.inteception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class InteceptionGlobal implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(InteceptionGlobal.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("请求url:{}",request.getRequestURL());
		return true;
	}

}
