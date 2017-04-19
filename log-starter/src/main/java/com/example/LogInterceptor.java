package com.example;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by tangzhilong on 17/4/19.
 */
public class LogInterceptor implements MethodInterceptor {
    private LogProperties logProperties;

    private static AntPathMatcher pathMatcher = new AntPathMatcher();
    private Logger logger = LoggerFactory.getLogger(LogAutoConfiguration.class);
    public LogInterceptor(LogProperties logProperties) {
        this.logProperties=logProperties;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        List<String> logReg = logProperties.getLogrexArr();
        boolean matchResult = false;
        for(String pattern : logReg) {
            matchResult=matchResult||pathMatcher.match(pattern,methodName);
        }
        Object result =null;
        if(matchResult) {
            long start = System.currentTimeMillis();
            result = invocation.proceed();
            long useTime = System.currentTimeMillis()-start;

            logger.info(MessageFormat.format("{0}方法耗时{1}milliseconds",invocation.getMethod().toGenericString(),useTime));
        }else {
            result = invocation.proceed();
        }

        return result;
    }
}
