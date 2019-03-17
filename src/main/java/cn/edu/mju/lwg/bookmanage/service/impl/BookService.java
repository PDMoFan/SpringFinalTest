package cn.edu.mju.lwg.bookmanage.service.impl;

import cn.edu.mju.lwg.bookmanage.dao.IBookDao;
import cn.edu.mju.lwg.bookmanage.entity.Book;
import cn.edu.mju.lwg.bookmanage.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class BookService implements IBookService {
    @Autowired
    private IBookDao iBookDao;
    @Override
    public String save(Book book) {
        List<Book> books=iBookDao.findAll();
        for (Book book1:books){
            if (book1.getId().equals(book.getId())){
                return "101";
            }
        }
        iBookDao.insert(book);
        return "100";
    }

    @Override
    public String update(Book book) {
        List<Book> books=iBookDao.findAll();
        for (Book book1:books){
            if (book1.getName().equals(book.getName())){
                return "101";
            }
        }
    iBookDao.update(book);
        return "100";
    }

    @Override
    public String delete(String id) {
        Book book=iBookDao.findById(id);
        if(book.getUser_id()!=null){
            return "101";
        }else {
            iBookDao.delete(id);
            return "100";
        }
    }

    @Override
    public List<Book> findAll() {
        return iBookDao.findAll();
    }

    @Override
    public Book findById(String id) {
        return iBookDao.findById(id);
    }

    @Override
    public List<Book> findByName(String name) {
        return iBookDao.findByName(name);
    }

    @Override
    public String deleteMore(List<String> ids) {
        for (String id:ids){
            Book book=iBookDao.findById(id);
            if(book.getUser_id()!=null){
                return "101";
            }
       }
        iBookDao.deleteMore(ids);
        return "100";
    }

    @Override
    public List<Book> findByBookId(String id) {
        return iBookDao.findByBookId(id);
    }
    @Transactional
    @Override
    public void reBack(String id) {
        Date date= new Date();
        //修改book表
        iBookDao.reBack(id,date);
        //修改用户表
        iBookDao.reBack2(id);
    }
    @Transactional
    @Override
    public void rentBook(String id, String user_id) {
        Date date=new Date();
        //修改book表
        iBookDao.rentBook(id,user_id,date);
        //修改用户表
        iBookDao.rentBook2(id,user_id);
    }

    @Override
    public void checkStatus(String id) {
        iBookDao.checkStatus(id);
    }

    @Override
    public List<Book> findAllByPage(Integer start, Integer count) {

        return iBookDao.findAllByPage((start-1)*count,count);
    }

}
