package cn.edu.mju.lwg.bookmanage.interceptor;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进行管理员身份检查");
        HttpSession session=request.getSession();
        Administrator administrator =(Administrator) session.getAttribute("admin");
        if (administrator!=null){
            return true;
        }
        //清空session
        session.invalidate();
        response.sendRedirect("/test/tologin/index");
        return false;
    }
}
