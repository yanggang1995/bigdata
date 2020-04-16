package com.reptile.proxy.yg.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 服务层切面
 *
 * @author Y.G
 * @date 2020/4/10 16:35
 **/
@Component
@Aspect
public class ServiceAop {
    private static final Logger logger = LoggerFactory.getLogger(ServiceAop.class);

    @Around("execution(* com.reptile.proxy.yg.service.*.*(..))")
    public Object intercept(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            long startTime = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            long executeTime = System.currentTimeMillis() - startTime;
            logger.info("Service: 参数{} , execute {} ms", Arrays.toString(args), executeTime);
            return result;
        }catch (Throwable e){
            logger.error("程序异常：{}", e.getMessage());
            logger.error(e.getMessage(), e);
            JSONObject response = new JSONObject();
            return response;
        }
    }
}
