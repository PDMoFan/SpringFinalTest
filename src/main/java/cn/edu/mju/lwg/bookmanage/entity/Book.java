package cn.edu.mju.lwg.bookmanage.entity;

import java.util.Date;
import java.util.List;

public class Book {
    private String id;
    private String name;
    private int inventory;
//    1可借阅，0不可借阅
    private boolean status;
    private String user_id;
    private Date start_date;
    private Date deadline;
    private User user;
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Book() {
    }

    public Book(String id, String name, int inventory, boolean status) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.status = status;
    }

    public Book(String id, String name, int inventory, boolean status, String user_id) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.status = status;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", inventory=" + inventory +
                ", status=" + status +
                ", user_id='" + user_id + '\'' +
                ", start_date=" + start_date +
                ", deadline=" + deadline +
                ", user=" + user +
                '}';
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
