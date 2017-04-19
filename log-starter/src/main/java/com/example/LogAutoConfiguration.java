package com.example;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by tangzhilong on 17/4/18.
 */
@Configuration
@EnableConfigurationProperties(LogProperties.class)
@Aspect
public class LogAutoConfiguration extends AbstractPointcutAdvisor{
    private Logger logger = LoggerFactory.getLogger(LogAutoConfiguration.class);

    @Autowired
    private LogProperties logProperties;
    private org.springframework.aop.Pointcut pointcut;
    private Advice advice;

    @PostConstruct
    private void init() {
        logger.info("LogAutoConfiguration init start");
        this.pointcut = new AnnotationMatchingPointcut(null, Log.class);
        this.advice = new LogInterceptor(logProperties);
    }

    @Override
    public org.springframework.aop.Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

}
