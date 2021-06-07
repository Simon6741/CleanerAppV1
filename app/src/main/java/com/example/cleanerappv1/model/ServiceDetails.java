package com.example.cleanerappv1.model;

public class ServiceDetails {
    private String address;
    private String booking_id;
    private String cid;
    private String date;
    private String extra;
    private String name;
    private String payment;
    private String phone;
    private String service;
    private String special;
    private String status;
    private String time;
    private String total_amount;
    private String uid;

    public ServiceDetails(){

    }

    public ServiceDetails(String address, String booking_id, String cid, String date, String extra, String name, String payment, String phone, String service, String special, String status, String time, String total_amount, String uid) {
        this.address = address;
        this.booking_id = booking_id;
        this.cid = cid;
        this.date = date;
        this.extra = extra;
        this.name = name;
        this.payment = payment;
        this.phone = phone;
        this.service = service;
        this.special = special;
        this.status = status;
        this.time = time;
        this.total_amount = total_amount;
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
