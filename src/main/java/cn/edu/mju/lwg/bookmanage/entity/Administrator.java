package cn.edu.mju.lwg.bookmanage.entity;

import java.util.List;

public class Administrator {
    private String id;
    private String name;
    private String password;
    private List<Book> books;
    private List<User> users;

    public Administrator() {
    }

    public Administrator(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
