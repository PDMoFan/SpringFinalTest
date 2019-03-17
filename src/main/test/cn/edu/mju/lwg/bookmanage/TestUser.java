package cn.edu.mju.lwg.bookmanage;

import cn.edu.mju.lwg.bookmanage.entity.User;
import cn.edu.mju.lwg.bookmanage.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUser {
    @Autowired
    private IUserService iUserService;
    @Test
    public void testInsert(){
        User user =new User("1","1","1");
        iUserService.save(user);
    }
    @Test
    public void testUpdate(){
        User user =new User("1","2","3");
        iUserService.update(user);
    }
    @Test
    public void testDelete(){
        iUserService.delete("1");
    }
    @Test
    public void testfindAll(){
        iUserService.findAll();
    }
}
