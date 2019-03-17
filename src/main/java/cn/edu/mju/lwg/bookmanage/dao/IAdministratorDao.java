package cn.edu.mju.lwg.bookmanage.dao;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;

import java.util.List;

public interface IAdministratorDao {
    void insert(Administrator administrator);
    void delete(String id);
    void update(Administrator administrator);
    List<Administrator> findAll();
}
