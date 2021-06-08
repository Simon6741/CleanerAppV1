package com.example.cleanerappv1.model;

import java.io.Serializable;

public class Customer{
   private String contactNumber;
   private String emailAddress;
   private String fullName;
   private String password;
   private String uid;
   private String userType;
   private String username;

   public Customer() {
   }

   public Customer(String contactNumber, String emailAddress, String fullName, String password, String uid, String userType, String username) {
      this.contactNumber = contactNumber;
      this.emailAddress = emailAddress;
      this.fullName = fullName;
      this.password = password;
      this.uid = uid;
      this.userType = userType;
      this.username = username;
   }

//   public Customer(String contactNumber, String emailAddress, String fullName, String uid, String username) {
//      this.contactNumber = contactNumber;
//      this.emailAddress = emailAddress;
//      this.fullName = fullName;
//      this.uid = uid;
//      this.username = username;
//   }

   public String getContactNumber() {
      return contactNumber;
   }

   public void setContactNumber(String contactNumber) {
      this.contactNumber = contactNumber;
   }

   public String getEmailAddress() {
      return emailAddress;
   }

   public void setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress;
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

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }


}
