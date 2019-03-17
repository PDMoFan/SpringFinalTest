package cn.edu.mju.lwg.bookmanage.service;

import cn.edu.mju.lwg.bookmanage.entity.Administrator;

import java.util.List;

public interface IAdministratorService {
    void save(Administrator administrator);
    void update(Administrator administrator);
    void delete(String id);
    List<Administrator> findAll();
}
