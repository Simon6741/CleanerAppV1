package com.example.cleanerappv1.model;

public class Cleaner {
    private String age, basicHouseKeeping, carCleaning, contact_no,email,fullname,gender,image,moveInOutCleaning,officeCommercialCleaning,password,springCleaning,uid,userType,userName;

    public Cleaner() {
    }

    public Cleaner(String age, String basicHouseKeeping, String carCleaning,
                   String contact_no, String email, String fullname, String gender,
                   String image, String moveInOutCleaning, String officeCommercialCleaning, String password,
                   String springCleaning, String uid, String userType, String userName) {
        this.age = age;
        this.basicHouseKeeping = basicHouseKeeping;
        this.carCleaning = carCleaning;
        this.contact_no = contact_no;
        this.email = email;
        this.fullname = fullname;
        this.gender = gender;
        this.image = image;
        this.moveInOutCleaning = moveInOutCleaning;
        this.officeCommercialCleaning = officeCommercialCleaning;
        this.password = password;
        this.springCleaning = springCleaning;
        this.uid = uid;
        this.userType = userType;
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBasicHouseKeeping() {
        return basicHouseKeeping;
    }

    public void setBasicHouseKeeping(String basicHouseKeeping) {
        this.basicHouseKeeping = basicHouseKeeping;
    }

    public String getCarCleaning() {
        return carCleaning;
    }

    public void setCarCleaning(String carCleaning) {
        this.carCleaning = carCleaning;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMoveInOutCleaning() {
        return moveInOutCleaning;
    }

    public void setMoveInOutCleaning(String moveInOutCleaning) {
        this.moveInOutCleaning = moveInOutCleaning;
    }

    public String getOfficeCommercialCleaning() {
        return officeCommercialCleaning;
    }

    public void setOfficeCommercialCleaning(String officeCommercialCleaning) {
        this.officeCommercialCleaning = officeCommercialCleaning;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpringCleaning() {
        return springCleaning;
    }

    public void setSpringCleaning(String springCleaning) {
        this.springCleaning = springCleaning;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
