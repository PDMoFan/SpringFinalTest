package cn.edu.mju.lwg.bookmanage.dao;

import cn.edu.mju.lwg.bookmanage.entity.User;

import java.util.List;

public interface IUserDao {
    void insert(User user);
    void delete(String id);
    void update(User user);
    void updatePass(User user);
    User findById(String id);
    //1对多查询，查询书籍被借阅的记录
    List<User> findByUserId(String book_id);
    List<User> findAll();
    //模糊查询
    List<User> findIds(String id);
    void deleteMore(String[] ids);
    //1对多查询,查询用户借阅书籍记录
    List<User> findAllWithBook(String id);
}
