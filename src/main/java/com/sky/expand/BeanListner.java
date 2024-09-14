package com.sky.expand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanListner implements BeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(BeanListner.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean,String beanName) throws BeansException {
		if(beanName.contains("book")) {
			logger.info(String.format("%s 即将初始化", beanName));
		}
		return bean;
	}
	
	@Override
	public Object postProcessAfterInitialization(Object bean,String beanName) throws BeansException {
		if(beanName.contains("book")) {
			logger.info(String.format("%s 初始化完成",beanName));
		}
		return bean;
    }
}
