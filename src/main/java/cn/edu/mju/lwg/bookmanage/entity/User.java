package cn.edu.mju.lwg.bookmanage.entity;

import java.util.List;

public class User {
    private String id;
    private String name;
    private String password;
    private String book_id;
    private Book book;
    private List<Book> books;
    public User() {
    }

    public User(String id, String name, String password, String book_id, Book book) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.book_id = book_id;
        this.book = book;
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", book_id='" + book_id + '\'' +
                ", book=" + book +
                '}';
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
