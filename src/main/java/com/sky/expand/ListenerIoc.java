package com.sky.expand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.naming.Context;

@Component
public class ListenerIoc {

    private Logger logger = LoggerFactory.getLogger(ListenerIoc.class);

    @EventListener(ApplicationStartedEvent.class)
    public void onEvent(ApplicationStartedEvent e) {
        logger.info("ioc启动了");
    }

    @EventListener(ContextClosedEvent.class)
    public void onEvent(ContextClosedEvent e) {
        logger.info("ioc关闭了");
    }

}
