package cn.edu.mju.lwg.bookmanage.action;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import cn.edu.mju.lwg.bookmanage.entity.User;
import cn.edu.mju.lwg.bookmanage.service.IAdministratorService;
import cn.edu.mju.lwg.bookmanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.rmi.transport.tcp.TCPTransport;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/tologin")
public class LoginAction {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IAdministratorService iAdministratorService;
    @RequestMapping("/index")
    public String index(){
        System.out.println("进入登陆系统");
//        return "/manage/user/index";
        return "/manage/login";
    }
    //登陆验证
    @RequestMapping("/login")
    public String check(User user, HttpSession session, HttpServletRequest request){
        System.out.println("进入登陆验证");
        System.out.println("userid="+user.getId()+"     userpass="+user.getPassword()
                +"    sessionCode="+session.getAttribute("code")+"       verity="+request.getParameter("verity"));
        //后台传入验证码
        String code=(String)session.getAttribute("code");
        //前端输入验证码
        String verity=request.getParameter("verity");
        if (user==null){
            session.setAttribute("logoinmessage","登陆失败，账号或密码错误，请重新登陆！");
            System.out.println("登陆失败！");
            return "redirect:index";
        }
        List<User> users=iUserService.findAll();
        List<Administrator> administrators=iAdministratorService.findAll();
        if (code.equals(verity))
        {
        for (User u:users){
                if (u.getId().equals(user.getId())&&u.getPassword().equals(user.getPassword()))
                {
                System.out.println("用户登陆成功！");
                session.setAttribute("tuser",u);
                return "redirect:/user/index";
                 }
            }
        }
            if (code.equals(verity)) {
                for (Administrator a : administrators) {
                    if (a.getId().equals(user.getId()) && a.getPassword().equals(user.getPassword())) {
                        System.out.println("管理员登陆成功！");
                        session.setAttribute("admin",a);
                        return "redirect:/admin/index";
                    }
                }
            }
        session.setAttribute("logoinmessage","登陆失败，账号或密码错误，请重新登陆！");
        System.out.println("登陆失败！");
        return "redirect:index";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/manage/register";
    }
    @RequestMapping("/saveuser")
    public String register(User user,HttpSession session){
        System.out.println("进入注册保存用户");
        System.out.println("userid="+user.getId());
        String tag=iUserService.save(user);
        if (tag.equals("201")){
            session.setAttribute("registumsg","注册的用户账号已存在，请重新输入！");
            return "redirect:toRegister";
        }
        return "redirect:index";
    }
    @RequestMapping("/tochangepass")
    public String toChangePass(){
        return "/manage/changepass";
    }

    @RequestMapping("/savepass")
    public String savePass(User user,HttpSession session){
        System.out.println("进入保存用户密码");
        System.out.println("userid="+user.getId());
        String tag=iUserService.updatePass(user);
        if (tag.equals("201")){
            session.setAttribute("chapassmsg","未找到该用户，请重新输入！");
            return "redirect:tochangepass";
        }
        if (tag.equals("202")){
            session.setAttribute("chapassmsg","旧密码错误，请重新输入！");
            return "redirect:tochangepass";
        }
        return "redirect:index";
    }

    /*
     * 生成验证码
     * */
    @RequestMapping("/captche")
    public void captche(HttpServletResponse response, HttpSession session){

        int width=70,height=60;
//        6位随机数法一
//        缺点首位不为0
//        int intFlag = (int)((Math.random()*9+1)* 100000);
//        String code=String.valueOf(intFlag);

        //        6位随机数法二
        Random random=new Random();
        String code="";
        for(int i=0;i<6;i++){
//     字符串和数字直接相加，是把数字当成了字符串，这是JAVA的装箱机制，最终相当于字符串的连接
//      也可使用StringBuffer用append拼接字符串
            code+=random.nextInt(10);
        }
        BufferedImage bufferedImage =new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Graphics g=bufferedImage.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        g.setColor(Color.red);
        g.drawString(code,20,30);
        g.dispose();
        try{
            OutputStream os=response.getOutputStream();
            ImageIO.write(bufferedImage,"JPEG",os);
            os.flush();
            session.setAttribute("code",code);
            os.close();
        }catch (Exception e){
            e.getMessage();
        }

    }
}
