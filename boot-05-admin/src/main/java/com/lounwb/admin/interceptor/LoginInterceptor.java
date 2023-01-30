package com.lounwb.admin.interceptor;

import com.lounwb.admin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检查
 * 1、配置好拦截器要拦截那些请求
 * 2、把这些配置放到容器中
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("请求地址为{}",requestURI);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user != null) {
            //放行
            return true;
        }else {
            //拦截,返回登录页面
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
    }
}
