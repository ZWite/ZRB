package com.zhang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author: 张孟鑫
 * @description:  实现Web层的日志切面
 * @date: 2022/6/7 14:09
 * @version 1.0
 */

/**
 * 注解来标识切面的优先级
 * i的值越小，优先级越高。假设我们还有一个切面是CheckNameAspect用来校验name必须为didi，我们为其设置@Order(10)，
 * 而上文中WebLogAspect设置为@Order(5)，所以WebLogAspect有更高的优先级，这个时候执行顺序是这样的：
 * 在@Before中优先执行@Order(5)的内容，再执行@Order(10)的内容
 * 在@After和@AfterReturning中优先执行@Order(10)的内容，再执行@Order(5)的内容
 */
@Slf4j
@Configuration
@Aspect
@Order(-5)
public class AspectPointcut {

    /**
     * 定义一个切入点.
     * 解释下：
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */

    @Pointcut("execution(public * com.zhang.*.*..*.*(..))")
    public void webLog(){}

//    @Before(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Before(value = "webLog()")
    public void before(JoinPoint joinPoint){
        log.info("AspectPointcut.before()");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=enu.nextElement();
            log.info(paraName+": "+request.getParameter(paraName));
        }
    }

    @After(value = "webLog()")
    public void after(JoinPoint joinPoint){
        log.info("AspectPointcut.after()");
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    @Around(value = "webLog()")
    public void around(){

    }
}
