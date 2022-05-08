package com.zhang.Interceptor;

import com.zhang.ThreadLocal.ContextManager;
import com.zhang.bean.BeanFactoryU;
import com.zhang.pojo.User;
import com.zhang.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: diexi
 * @Date: 2022/5/8 12:33
 * @ClassName: UserThreadLocal
 */
@Slf4j
public class UserThreadLocal implements HandlerInterceptor {

//    @Autowired
//    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        RedisUtils redisUtils = BeanFactoryU.getBean("redisUtils");
        log.info("拦截器2开始");
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        if (token != null) {
            User user = (User) redisUtils.get(token);
            ContextManager.setContextData(User.class,user);
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("拦截器2释放");
        ContextManager.clearContextData();
    }

}
