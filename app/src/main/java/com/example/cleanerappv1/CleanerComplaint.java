package com.example.cleanerappv1;

public class CleanerComplaint {

    private String Name, Reason, uid, complaint_id;


    public CleanerComplaint() {
    }

    public CleanerComplaint(String Name, String Reason, String complaint_id, String uid) {
        this.Name = Name;
        this.Reason = Reason;
        this.complaint_id = complaint_id;
        this.uid = uid;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(String complaint_id) {
        this.complaint_id = complaint_id;
    }
}