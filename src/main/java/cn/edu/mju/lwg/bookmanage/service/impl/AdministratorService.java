package cn.edu.mju.lwg.bookmanage.service.impl;

import cn.edu.mju.lwg.bookmanage.dao.IAdministratorDao;
import cn.edu.mju.lwg.bookmanage.entity.Administrator;
import cn.edu.mju.lwg.bookmanage.service.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdministratorService implements IAdministratorService {
    @Autowired
    private IAdministratorDao iAdministratorDao;
    @Override
    public void save(Administrator administrator) {
    iAdministratorDao.insert(administrator);
    }

    @Override
    public void update(Administrator administrator) {
iAdministratorDao.update(administrator);
    }

    @Override
    public void delete(String id) {
iAdministratorDao.delete(id);
    }

    @Override
    public List<Administrator> findAll() {
        return iAdministratorDao.findAll();
    }
}
