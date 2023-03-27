package com.sgcc.code.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sgcc.code.annotate.PassToken;
import com.sgcc.code.entity.User;
import com.sgcc.code.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器，登录检查
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        logger.info("进入拦截器>>>>>>>>>>>>>>>>>>>>>>>");

        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(httpServletRequest.getServletContext());

        if(cxt != null && cxt.getBean("userServiceImpl") != null && userService == null) {
            userService = (UserService) cxt.getBean("userServiceImpl"); // weixinUserService 是实现类的别名
        }

        String token = httpServletRequest.getHeader("authorization");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }else{
            // 执行认证
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            // 获取 token 中的 user id
            String username;
            try {
                username = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username", username);
            User user = userService.getOne(wrapper);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("401");
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}