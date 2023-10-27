package com.zhang.Interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zhang.Interface.PassToken;
import com.zhang.Interface.ServiceAop;
import com.zhang.Interface.UserLoginToken;
import com.zhang.ThreadLocal.ContextManager;
import com.zhang.aop.AspectPointcut;
import com.zhang.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: diexi
 * @Date: 2022/3/26 23:54
 * @ClassName: AuthenticationInterceptor
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("拦截器1开始");
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        // 获取方法
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class) || method.getDeclaringClass().isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)|| method.getDeclaringClass().isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class) != null ? method.getAnnotation(UserLoginToken.class) :method.getDeclaringClass().getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                //将token存入ThreadLocal
                ContextManager.setContextData("token",token);
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("QIYU")).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
                    throw new RuntimeException("The Token has expired");
                }
                return true;
            }
        }
        //检查是否有AOP注解，有AOP注解执行枷锁方法
        if (method.isAnnotationPresent(ServiceAop.class) || method.getDeclaringClass().isAnnotationPresent(ServiceAop.class)){
            log.info("有AOP注解执行枷锁方法");
            try {
                Class<AspectPointcut> aspectPointcutClass = AspectPointcut.class;
                Method before = aspectPointcutClass.getMethod("before", JoinPoint.class);
                before.invoke(JoinPoint.class);
            } catch (Exception e){
                throw new RuntimeException("The AOP has expired");
            }
            log.info("有AOP注解执行枷锁方法结束");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
