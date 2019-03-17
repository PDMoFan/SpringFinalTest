package cn.edu.mju.lwg.bookmanage.service.impl;

import cn.edu.mju.lwg.bookmanage.dao.IUserDao;
import cn.edu.mju.lwg.bookmanage.entity.User;
import cn.edu.mju.lwg.bookmanage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Override
    public String save(User user) {
       List<User> users=iUserDao.findAll();
       for (User user1:users){
           if (user1.getId().equals(user.getId())){
               return "201";
           }
       }
    iUserDao.insert(user);
       return "200";
    }

    @Override
    public void update(User user) {
iUserDao.update(user);
    }

    @Override
    public String updatePass(User user) {
        List<User> users =iUserDao.findAll();
        for (User user1:users){
        if (user1.getId().equals(user.getId())){
            if (user.getName().equals(user1.getPassword())){
                iUserDao.updatePass(user);
                return "200";
            }
            else {
                return "202";
            }
        }
        }
        return "201";
    }

    @Override
    public String delete(String id) {
        User user=iUserDao.findById(id);
        if (user.getBook_id()!=null){
            return "201";
        }else {
        iUserDao.delete(id);
        return "200";
        }

    }

    @Override
    public User findById(String id) {
        return iUserDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public List<User> findByUserId(String id) {
        return iUserDao.findByUserId(id);
    }

    @Override
    public String deleteMore(String[] ids) {
        for (String id:ids){
            User user=iUserDao.findById(id);
            if (user.getBook_id()!=null){
                return "201";
            }
        }
        iUserDao.deleteMore(ids);
        return "200";
    }

    @Override
    public List<User> findIds(String id) {
        return iUserDao.findIds(id);
    }

    @Override
    public List<User> findAllWithBook(String id) {
        return iUserDao.findAllWithBook(id);
    }


}
