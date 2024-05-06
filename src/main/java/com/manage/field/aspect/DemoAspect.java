package com.manage.field.aspect;

import com.manage.field.annotation.DemoAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class DemoAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.manage.field.annotation.DemoAnnotation)")
    public void pointCut() {}

    /**
     * 环绕通知
     */
    @Around("pointCut()")
    public Object aspectAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行方法
        Object jsonResult = joinPoint.proceed();
        System.out.println(jsonResult);
        try {
            // 获取请求签名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取注解值
            DemoAnnotation demoAnnotation = method.getAnnotation(DemoAnnotation.class);
            // 获取属性
            String value = demoAnnotation.value();
            System.out.println(value);
            String desc = demoAnnotation.desc();
            System.out.println(desc);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return jsonResult;
    }
}
