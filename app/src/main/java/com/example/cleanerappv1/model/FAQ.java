package com.example.cleanerappv1.model;

public class FAQ {

    private String Title;
    private String detail;

    public FAQ(String title, String detail) {
        Title = title;
        this.detail = detail;
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
}
