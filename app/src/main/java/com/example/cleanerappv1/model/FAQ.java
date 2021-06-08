package com.example.cleanerappv1.model;

public class FAQ {

    private String Title;
    private String detail;
    private String id;

    public FAQ(String title, String detail, String id) {
        Title = title;
        this.detail = detail;
        this.id = id;
    }

    public FAQ() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
