package com.example.cleanerappv1.model;

public class Complain {

    private String Name;
    private String Reason;
    private String complaint_id;
    private String uid;

    public Complain(String name, String reason, String complaint_id, String uid) {
        Name = name;
        Reason = reason;
        this.complaint_id = complaint_id;
        this.uid = uid;
    }

    public Complain() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
