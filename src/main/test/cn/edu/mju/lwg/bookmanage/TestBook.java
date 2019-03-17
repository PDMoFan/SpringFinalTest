package cn.edu.mju.lwg.bookmanage;

import cn.edu.mju.lwg.bookmanage.entity.Book;
import cn.edu.mju.lwg.bookmanage.service.IBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBook {
    @Autowired
    private IBookService iBookService;
    @Test
    public void testInsert(){
        Book book=new Book("1","1",5,true);
        iBookService.save(book);
    }
    @Test
    public void testUpdate(){
        Book book=new Book("1","3",4,true);
        iBookService.update(book);
    }
    @Test
    public void testDelete(){
        iBookService.delete("1");
    }
    @Test
    public void testfindAll(){
        iBookService.findAll();
    }
    @Test
    public void testfindName(){
        iBookService.findByName("3e");
    }
}
