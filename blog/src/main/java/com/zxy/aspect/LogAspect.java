package com.zxy.aspect;

//dependencies

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

//required notation
@Aspect
@Component
public class LogAspect {
    //initiate an instance of Logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //stop all parameters in the web directory
    @Pointcut("execution(* com.zxy.web.*.*(..))")
    public void log() {
    }

    //execute before getting an instance of the log
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //get all attributes associated with a request
        HttpServletRequest request = attributes.getRequest();
        //get the url that requested
        String url = request.getRequestURL().toString();
        //get the ip that requested
        String ip = request.getRemoteAddr();
        //add attributes to request log
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}", requestLog);
    }

    //execute after getting an instance of the log
    @After("log()")
    public void doAfter() {
        //logger.info("--------doAfter--------");
    }

    //execute after returning an instance of the log
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterRuturn(Object result) {
        logger.info("Result : {}", result);
    }

    //define the request log class
    private class RequestLog {
        //attributes
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        //class constructor
        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        //toString method
        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
