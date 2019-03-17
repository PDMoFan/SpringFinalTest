package cn.edu.mju.lwg.bookmanage.interceptor;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import cn.edu.mju.lwg.bookmanage.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        System.out.println("登陆拦截！");
        User u=(User)session.getAttribute("tuser");
        Administrator administrator =(Administrator) session.getAttribute("admin");
        if (u==null&&administrator==null){
            response.sendRedirect("/test/tologin/index");
            return false;
        }else{
            return true;
        }

    }
}
