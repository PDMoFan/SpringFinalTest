package cn.edu.mju.lwg.bookmanage;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import cn.edu.mju.lwg.bookmanage.service.IAdministratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAdmin {
    @Autowired
    private IAdministratorService iAdministratorService;
    @Test
    public void testInsert(){
        Administrator administrator=new Administrator("1","1","1");
iAdministratorService.save(administrator);
    }
    @Test
    public void testUpdate(){
        Administrator administrator=new Administrator("1","2","1");
iAdministratorService.update(administrator);
    }
    @Test
    public void testDelete(){
iAdministratorService.delete("1");
    }
    @Test
    public void testfindAll(){
        iAdministratorService.findAll();
    }

}
