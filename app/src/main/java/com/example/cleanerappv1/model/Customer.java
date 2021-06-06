package com.example.cleanerappv1.model;

public class Customer {
   private String contactNumber;
   private String email;
   private String fullName;
   private String password;
   private String uId;
   private String userType;
   private String userName;

   public Customer() {
   }

   public Customer(String contactNumber, String email, String fullName, String password, String uId, String userType, String userName) {
      this.contactNumber = contactNumber;
      this.email = email;
      this.fullName = fullName;
      this.password = password;
      this.uId = uId;
      this.userType = userType;
      this.userName = userName;
   }

   public String getContactNumber() {
      return contactNumber;
   }

   public void setContactNumber(String contactNumber) {
      this.contactNumber = contactNumber;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFullName() {
      return fullName;
   }

   public void setFullName(String fullName) {
      this.fullName = fullName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getuId() {
      return uId;
   }

   public void setuId(String uId) {
      this.uId = uId;
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
