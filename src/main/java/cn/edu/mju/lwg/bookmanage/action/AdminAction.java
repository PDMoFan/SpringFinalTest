package cn.edu.mju.lwg.bookmanage.action;

import cn.edu.mju.lwg.bookmanage.dao.IUserDao;
import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import cn.edu.mju.lwg.bookmanage.entity.Book;
import cn.edu.mju.lwg.bookmanage.entity.Pagination;
import cn.edu.mju.lwg.bookmanage.entity.User;
import cn.edu.mju.lwg.bookmanage.service.IAdministratorService;
import cn.edu.mju.lwg.bookmanage.service.IBookService;
import cn.edu.mju.lwg.bookmanage.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Autowired
    private IAdministratorService iAdministratorService;
    @Autowired
    private IBookService iBookService;
    @Autowired
    private IUserService iUserService;
    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        System.out.println("进入图书管理系统");
//        return "/manage/user/index";
        Administrator administrator=(Administrator) session.getAttribute("admin");
        System.out.println("name="+administrator.getName());
        ModelAndView mv=new ModelAndView();
        mv.addObject("administrator",administrator);
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

        mv.setViewName("/manage/admin/index");
        return mv;
    }


        @RequestMapping("/index/{toPage}")
    public String indexPage(@PathVariable String toPage,HttpSession session){
        System.out.println("进入图书分页查看管理系统");
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
            System.out.println("最后itoPage="+itoPage);
        session.setAttribute("page",page);
        return "redirect:../index";
    }
    /*
    * 注销
    * */
    @RequestMapping("/cancel")
    public String cancel(HttpSession session){
        //清空session中的信息
        session.invalidate();
        return "redirect:index";
    }

    @RequestMapping("/addbook")
    public ModelAndView addBook(){
        System.out.println("进入添加图书系统");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/manage/admin/addbook");
        return mv;
    }
    @RequestMapping("/savebook")
    public String saveBook(Book book,HttpSession session){
        System.out.println("进入保存图书系统");
        String tag=iBookService.save(book);
        if (tag.equals("101")){
            session.setAttribute("addbkmsg","库中已存在该书籍id，请重新填写");
            return "redirect:addbook";
        }
       return "redirect:index";
    }
    @RequestMapping("/updatebook")
    public String updateBook(Book book,HttpSession session){
        System.out.println("进入更新图书系统");
        String tag=iBookService.update(book);
        if (tag.equals("101")){
            session.setAttribute("updatebkmsg","库中已经存在同名书籍，请重新输入");
            return "redirect:./editbook/"+book.getId();
        }
       return "redirect:index";
    }
    @RequestMapping("/deletebook/{id}")
    public String saveBook(@PathVariable String id,HttpSession session){
        System.out.println("进入删除图书系统");
        System.out.println("id="+id);
        String tag=iBookService.delete(id);
        if (tag.equals("101")){
            session.setAttribute("delbkmsg","本书籍已被借阅，未归还，不能删除");
            return "redirect:../index";
        }
       return "redirect:../index";
    }
    @RequestMapping("/delMorebook")
    public String saveBook(String[] ids,HttpSession session){
        if (ids==null){
            return "redirect:index";
        }
        System.out.println("进入批量删除图书系统");
        for (String s : ids) {
            System.out.println("id="+s);
        }
        List<String> strings= Arrays.asList(ids);
        String tag=iBookService.deleteMore(strings);
        if (tag.equals("101")){
            session.setAttribute("delbkmsg","批量删除中存在书籍已被借阅，未归还，不能删除");
            return "redirect:index";
        }
       return "redirect:index";
    }

    @RequestMapping("/editbook/{id}")
    public ModelAndView editBook(@PathVariable String id){
        System.out.println("进入修改图书系统");
        Book book=iBookService.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("book",book);
        mv.setViewName("/manage/admin/editbook");
        return mv;
    }
    @RequestMapping("/searchbook")
    public ModelAndView searchBook(String name){
        System.out.println("进入搜索图书系统");
        System.out.println("name="+name);
        List<Book> books=iBookService.findByName(name);
        ModelAndView mv=new ModelAndView();
        mv.addObject("books",books);
        mv.setViewName("/manage/admin/searchbk");
        return mv;
    }
    @RequestMapping("/usermanage")
    public ModelAndView userIndex(){
        ModelAndView mv=new ModelAndView();
        List<User> users=iUserService.findAll();
        mv.addObject("users",users);
        mv.setViewName("/manage/admin/usermanage");
        return mv;
    }
    @RequestMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable String id,HttpSession session){
        System.out.println("进入删除单用户");
        System.out.println("userid="+id);
        //外键不能删，判断是否外键
        String tag=iUserService.delete(id);
        if (tag.equals("201")){
            session.setAttribute("delusermsg","该用户有借阅书籍，待书籍归还后可以删除！");
            return "redirect:../usermanage";
        }
        return "redirect:../usermanage";
    }
    @RequestMapping("/edituser/{id}")
    public ModelAndView editUser(@PathVariable String id){
        System.out.println("进入修改用户界面");
        ModelAndView mv=new ModelAndView();
        User user=iUserService.findById(id);
        mv.addObject("user",user);
        mv.setViewName("/manage/admin/edituser");
        return mv;
    }
    @RequestMapping("/updateuser")
    public String updateUser(User user){
        System.out.println("进入修改用户");
        System.out.println("userid="+user.getId());
        iUserService.update(user);
        return "redirect:usermanage";
    }
    @RequestMapping("/adduser")
    public ModelAndView addUser(){
        System.out.println("进入添加用户界面");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/manage/admin/adduser");
        return mv;
    }
    @RequestMapping("/saveuser")
    public String saveUser(User user,HttpSession session){
        System.out.println("进入保存用户");
        System.out.println("userid="+user.getId());
        String tag=iUserService.save(user);
        if (tag.equals("201")){
            session.setAttribute("saveumsg","添加的用户账号已存在，请重新输入！");
            return "redirect:adduser";
        }
        return "redirect:usermanage";
    }
    @RequestMapping("/deleteusers")
    public String deleteUsers(String[] ids,HttpSession session){
        if (ids==null){
            return "redirect:usermanage";
        }
        System.out.println("进入批删用户！");
        for (String s :ids) {
            System.out.println("id="+s);
        }
        String tag=iUserService.deleteMore(ids);
        if (tag.equals("201")){
            session.setAttribute("delusermsg","删除的用户中存在借阅书籍未归还，待书籍归还后可以删除！");
            return "redirect:usermanage";
        }
        return "redirect:usermanage";
    }
    @RequestMapping("/searchuser")
    public ModelAndView searchUser(@Param(value = "id") String id){
        System.out.println("进入模糊查询用户");
        System.out.println("id="+id);
        ModelAndView mv=new ModelAndView();
        List<User> users=iUserService.findIds(id);
        mv.addObject("users",users);
        mv.setViewName("/manage/admin/usermanage");
        return mv;
    }
    @RequestMapping("/finduser/{id}")
    public ModelAndView findUser(@PathVariable String id){
        System.out.println("查看书籍借阅人");
        System.out.println("id="+id);
        List<Book> books=iBookService.findByBookId(id);
        for (Book book:books){
            System.out.println(book.toString());
            for (User user:book.getUsers()){
                System.out.println(user.toString());
            }
        }

        System.out.println("**********************");
        ModelAndView mv=new ModelAndView();
        mv.addObject("books",books);
        mv.addObject("bkid",id);
        mv.setViewName("/manage/admin/booklending");
        return mv;
    }
    /*
    *id：用户id
    *
    * */
    @RequestMapping("/findbook/{id}")
    public ModelAndView findBook(@PathVariable String id){
        System.out.println("进入查询用户借阅图书记录");
        System.out.println("id="+id);
        List<User> users = iUserService.findAllWithBook(id);
        for (User user:users){
            System.out.println(user.toString());
            for (Book book:user.getBooks()){
                System.out.println(book.toString());
            }
        }

        ModelAndView mv=new ModelAndView();
        mv.addObject("users",users);
        mv.setViewName("/manage/admin/userlend");
        return mv;
    }

    /*
     * 还书
     * id:书籍id
     * */
    @RequestMapping("/comeback/{bid}&{uid}")
    public String comeBack(@PathVariable String bid,@PathVariable String uid){
        System.out.println("进入图书归还");
        System.out.println("bid="+bid);
        System.out.println("uid="+uid);
        iBookService.reBack(bid);
        return "redirect:../findbook/"+uid;
    }

    /*
     * 借书
     * */
    @Transactional
    @RequestMapping("/rentbook")
    public String rentBook(@RequestParam String bkid,@RequestParam String uid,HttpSession session){
        if (uid==null||uid.equals("")){
            return "redirect:./finduser/"+bkid;
        }
        System.out.println("进入用户借阅图书");
        System.out.println("书籍id="+bkid);
        System.out.println("用户id="+uid);
        Book book=iBookService.findById(bkid);
        //检查图书库存
        if (book.getInventory()==0){
            //修改图书借阅状态
            iBookService.checkStatus(bkid);
            return "redirect:./finduser/"+bkid;
        }
        //检查该用户是否已借阅过此书
        if (book.getUser_id()!=null){
        String userid=book.getUser_id();
        if (userid.equals(uid)){
            return "redirect:./finduser/"+bkid;
        }
        }
        //检查输入的用户id是否存在
        List<User> userList=iUserService.findAll();
        for (User user:userList){
            //用户存在
            if (user.getId().equals(uid)){
                //检查mybatis中的rentBook，的userid和date
                iBookService.rentBook(bkid,uid);
                book=iBookService.findById(bkid);
                if (book.getInventory()==0){
                    //修改图书借阅状态
                    iBookService.checkStatus(bkid);
                }
            }
        }

        return "redirect:./finduser/"+bkid;
    }
}
