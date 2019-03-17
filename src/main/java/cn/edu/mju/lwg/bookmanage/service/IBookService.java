package cn.edu.mju.lwg.bookmanage.service;

import cn.edu.mju.lwg.bookmanage.entity.Book;
import cn.edu.mju.lwg.bookmanage.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IBookService {
    String save(Book book);
    String update(Book book);
    String delete(String id);
    List<Book> findAll();
    Book findById(String id);
    List<Book> findByName(String name);
    String deleteMore(List<String> ids);
    //查询书籍借阅人
    List<Book> findByBookId(String id);
    //图书归还,书籍id
    void reBack(String id);
    //借阅图书
    void rentBook(String id,String user_id);
    //检查书籍借阅状态
    void checkStatus(String id);
    //分页查看所有书籍
    List<Book> findAllByPage(Integer start,Integer count);

}
