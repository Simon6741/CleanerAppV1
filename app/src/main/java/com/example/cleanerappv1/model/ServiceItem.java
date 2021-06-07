package com.example.cleanerappv1.model;

public class ServiceItem {
    private String customerName;
    private String contactNum;
    private String email;
    private String cleanerName;
    private String cleanerContact;
    private String bookingStatus;

    public ServiceItem(){

    }

    public ServiceItem(String customerName, String contactNum, String email, String cleanerName, String cleanerContact, String bookingStatus) {
        this.customerName = customerName;
        this.contactNum = contactNum;
        this.email = email;
        this.cleanerName = cleanerName;
        this.cleanerContact = cleanerContact;
        this.bookingStatus = bookingStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCleanerName() {
        return cleanerName;
    }

    public void setCleanerName(String cleanerName) {
        this.cleanerName = cleanerName;
    }

    public String getCleanerContact() {
        return cleanerContact;
    }

    public void setCleanerContact(String cleanerContact) {
        this.cleanerContact = cleanerContact;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
