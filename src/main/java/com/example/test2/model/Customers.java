package com.example.test2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the customers model class*/
public class Customers {
    private String customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String divisionId;
    private String divisionName;
    ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();


    /**This is the getter to get the customer ID*/
    public String getCustomerId() {
        return customerId;
    }

    /**This is the setter to set the customer ID*/
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**This is a getter to get division name*/
    public String getDivisionName() {
        return divisionName;
    }

    /**This is a setter to set division name*/
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**This is the getter to get the customer name*/
    public String getCustomerName() {
        return customerName;
    }

    /**This is a setter to set the customer name*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**This is the getter to get the customer address*/
    public String getAddress() {
        return address;
    }

    /**This is a setter to set the customer address*/
    public void setAddress(String address) {
        this.address = address;
    }

    /**This is the getter to get the postal code for customers*/
    public String getPostalCode() {
        return postalCode;
    }

    /**This is a setter to set the postal code for customers*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**This is the getter to get the phone number for customers*/
    public String getPhone() {
        return phone;
    }

    /**This is a setter to set the phone number for customers*/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**This is a getter to get the division ID of a customer*/
    public String getDivisionId() {
        return divisionId;
    }

    /**This is a setter to set the division ID of a customer*/
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    /**This is a getter to get the list of appointments for a customer*/
    public ObservableList<Appointments> getAppointmentsList() {
        return appointmentsList;
    }

    /**This is the constructor that uses all the gathered information to create a new customer object*/
    public Customers(String customerId, String customerName, String address, String postalCode, String phone, String divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /**This is the constructor that uses all the gathered information without the customer ID to update a customer object*/
    public Customers(String customerName, String address, String postalCode, String phone, String divisionId) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /**This is a constructor for a customers object that only uses the customer ID*/
    public Customers(String customerId) {
        this.customerId = customerId;
    }

    /**This is a toString method to return customer ID as a string*/
    @Override
    public String toString() {
        return customerId;
    }
}
