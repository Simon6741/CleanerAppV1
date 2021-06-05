package com.example.cleanerappv1;

public class CleanerTask {
    private String task_id, book_id, uid;


    public CleanerTask() {
    }

    public CleanerTask(String task_id, String book_id,  String uid) {
        this.task_id = task_id;
        this.book_id = book_id;
        this.uid = uid;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
