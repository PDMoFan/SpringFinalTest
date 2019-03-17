package cn.edu.mju.lwg.bookmanage.dao;

import cn.edu.mju.lwg.bookmanage.entity.Book;
import cn.edu.mju.lwg.bookmanage.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IBookDao {
    void insert(Book book);
    void delete(String id);
    void update(Book book);
    List<Book> findAll();
    //分页查看所有书籍
    List<Book> findAllByPage(@Param("start") Integer start,@Param("count") Integer count);
    //1对多查询借阅书籍
    List<Book> findByUserId(String user_id);
    Book findById(String id);
    //模糊查询
    List<Book> findByName(String name);
    //批量删除
    void deleteMore(List<String> ids);
    //1对多查询书籍借阅人
    List<Book> findByBookId(String id);
    //图书归还
    //修改book表
    void reBack(@Param("id")String id,@Param("date")Date date);
    void reBack2(@Param("id")String id);
    //借阅图书
    //修改book表
    void rentBook(@Param("id") String id,@Param("user_id") String user_id,@Param("date") Date date);
    //修改user表
    void rentBook2(@Param("id") String id,@Param("user_id") String user_id);
    //修改书籍借阅状态
    void checkStatus(String id);
}
