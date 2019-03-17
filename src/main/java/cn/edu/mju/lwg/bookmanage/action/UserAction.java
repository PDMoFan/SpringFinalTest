package cn.edu.mju.lwg.bookmanage.action;

import cn.edu.mju.lwg.bookmanage.entity.Book;
import cn.edu.mju.lwg.bookmanage.entity.Pagination;
import cn.edu.mju.lwg.bookmanage.entity.User;
import cn.edu.mju.lwg.bookmanage.service.IBookService;
import cn.edu.mju.lwg.bookmanage.service.IUserService;
import cn.edu.mju.lwg.bookmanage.service.impl.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/user")
@Controller
public class UserAction {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IBookService iBookService;
    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        System.out.println("进入用户借阅图书界面");
        User tuser=(User)session.getAttribute("tuser");
        ModelAndView mv = new ModelAndView();
        mv.addObject("tuser",tuser);

        if (session.getAttribute("page")==null||session.getAttribute("page")=="")
        {
            List<Book> bookList=iBookService.findAll();
            //获取当前总记录数
            int num=0;
            for (Book b:bookList){
                num++;
            }
            Pagination page=new Pagination(1,5,num);
            //获取总页数
            int tn=page.getTotalPage();
            session.setAttribute("page",page);
            List<Book> books=iBookService.findAllByPage(1,page.getCount());
            mv.addObject("books",books);
        }
        else {
            Pagination page=(Pagination) session.getAttribute("page");
            System.out.println("page当前页码="+page.getStart());
            List<Book> books=iBookService.findAllByPage(page.getStart(),page.getCount());
            mv.addObject("books",books);
        }

        mv.setViewName("/manage/user/index");
        return mv;
    }
    @RequestMapping("/index/{toPage}")
    public String toPage(@PathVariable String toPage,HttpSession session){
        System.out.println("进入图书借阅分页查看");
        System.out.println("toPage="+toPage);
        List<Book> bookList=iBookService.findAll();
        //获取当前总记录数
        int num=0;
        for (Book b:bookList){
            num++;
        }
        //获取总页数
        Pagination page=new Pagination(1,5,num);
        int tn=page.getTotalPage();
        int itoPage=Integer.valueOf(toPage);
        if (itoPage>tn||itoPage<=0){
            page.setStart(1);
        }
        else{
            page.setStart(itoPage);
        }
        System.out.println("跳转页码itoPage="+itoPage);
        session.setAttribute("page",page);
        return "redirect:../index";
    }
    @RequestMapping("/cancel")
public String cancel(HttpSession session){
        //清空session中的信息
        session.invalidate();
        return "redirect:index";
}
    @RequestMapping("/searchbook")
    public ModelAndView searchBook(String name){
        System.out.println("进入搜索图书系统");
        System.out.println("name="+name);
        List<Book> books=iBookService.findByName(name);
        ModelAndView mv=new ModelAndView();
        mv.addObject("books",books);
        mv.setViewName("/manage/user/searchbk");
        return mv;
    }


    @RequestMapping("/recordrent")
    public ModelAndView recordRent(HttpSession session){
        System.out.println("进入用户借阅书籍记录界面");
        User user=(User)session.getAttribute("tuser");
        ModelAndView mv = new ModelAndView();
        List<User> users=iUserService.findAllWithBook(user.getId());
        mv.addObject("users",users);
        mv.setViewName("/manage/user/recordrent");
        return mv;
    }

    /*
    * 还书
    * */
    @RequestMapping("/comeback/{id}")
    public String comeBack(@PathVariable String id){
        System.out.println("进入图书归还");
        System.out.println("id="+id);
        iBookService.reBack(id);
        return "redirect:../recordrent";
    }
/*
* 借书
* */
    @Transactional
    @RequestMapping("/rentbook/{id}")
    public String rentBook(@PathVariable String id,HttpSession session){
        System.out.println("进入借阅图书");
        System.out.println("id="+id);
        User user =(User) session.getAttribute("tuser");
        Book book=iBookService.findById(id);
        if (book.getInventory()==0){
            //修改图书借阅状态
            iBookService.checkStatus(id);
            return "redirect:../index";
        }
        //检查mybatis中的rentBook，的userid和date
        iBookService.rentBook(id,user.getId());
        book=iBookService.findById(id);
        if (book.getInventory()==0){
        //修改图书借阅状态
        iBookService.checkStatus(id);
        }
        return "redirect:../index";
    }
}
