package cn.edu.mju.lwg.bookmanage.service;

import cn.edu.mju.lwg.bookmanage.entity.User;

import java.util.List;

public interface IUserService {

    String save(User user);
    void update(User user);
    //修改密码
    String updatePass(User user);
    String delete(String id);
    User findById(String id);
    List<User> findAll();
    //1对多查询，查询书籍被借阅的记录
    List<User> findByUserId(String id);
    String deleteMore(String[] ids);
    //模糊查询
    List<User> findIds(String id);
    //1对多查询,查询用户借阅书籍记录
    List<User> findAllWithBook(String id);

}
