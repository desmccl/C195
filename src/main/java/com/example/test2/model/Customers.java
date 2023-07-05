package com.example.test2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customers {
    private String customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String divisionId;
    private String divisionName;
    ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }
    public void addAppointments (Appointments appointments) {
        appointmentsList.add(appointments);
    }

    public ObservableList<Appointments> getAppointmentsList() {
        return appointmentsList;
    }

    public Customers(String customerId, String customerName, String address, String postalCode, String phone, String divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    public Customers(String customerName, String address, String postalCode, String phone, String divisionId) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }
}
