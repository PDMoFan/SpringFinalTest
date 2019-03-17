package cn.edu.mju.lwg.bookmanage.entity;

public class Pagination {
    private int start;//开始页
    private int count=5;//每页记录数
    private int total;//总记录数
    private int totalPage;//总页数
    private Boolean hasPrev;//是否有上页
    private Boolean hasNext;//是否有下页

    public Pagination(int start, int count, int total) {
        this.start = start;
        this.count = count;
        this.total = total;
    }

    public Pagination(int start, int count) {
        this.start = start;
        this.count = count;
    }

    public Pagination() {
        super();
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        if (total%count==0){
            totalPage=total/count;
        }else {
            totalPage=total/count+1;
        }
        return totalPage;
    }


    public Boolean getHasPrev() {
        hasPrev=start>1?true:false;
        return hasPrev;
    }

    public Boolean getHasNext() {
        hasNext=start<totalPage?true:false;
        return hasNext;
    }

}
