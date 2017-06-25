package com.xyj.shiro.inteceptor;

import com.xyj.shiro.annotation.CrmPermission;
import org.springframework.http.HttpRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by banma on 2017/6/21.
 */
public class VerifyInteceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println("过滤成功");

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            if (isHasPermition(handlerMethod, httpServletRequest)) {
                return true;
            } else
                return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean isHasPermition(HandlerMethod handlerMethod, HttpServletRequest request) {
        CrmPermission crmPermission = handlerMethod.getMethodAnnotation(CrmPermission.class);
        if (null == crmPermission) {
            Class<?> clazz = handlerMethod.getBeanType();
            if (clazz.isAnnotationPresent(CrmPermission.class)) {
                crmPermission = clazz.getAnnotation(CrmPermission.class);
            }
        }

        return isPassPermiton(crmPermission, request);
    }

    private boolean isPassPermiton(CrmPermission crmPermission, HttpServletRequest request) {
        if (crmPermission != null) {
            System.out.println(crmPermission.value());
        }
        System.out.println("权限通过");
        return true;
    }

}
