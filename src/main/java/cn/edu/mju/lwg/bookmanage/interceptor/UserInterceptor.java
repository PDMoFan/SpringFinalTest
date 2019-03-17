package cn.edu.mju.lwg.bookmanage.interceptor;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import cn.edu.mju.lwg.bookmanage.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进行用户身份检查");
        HttpSession session=request.getSession();
        User user =(User) session.getAttribute("tuser");
        if (user!=null){
            return true;
        }
        //清空session
        session.invalidate();
        response.sendRedirect("/test/tologin/index");
        return false;
    }
}
