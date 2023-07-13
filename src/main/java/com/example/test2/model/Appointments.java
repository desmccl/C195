package com.example.test2.model;

import java.time.LocalDateTime;

/**This is the appointments model class*/
public class Appointments {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private String customerId;
    private int userId;
    private String contactId;
    private String contactName;


    /**This the getter to get contact name*/
    public String getContactName() {
        return contactName;
    }
    /**This the setter to set contact name*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**This is the getter to get appointment ID*/
    public int getAppointmentId() {
        return appointmentId;
    }

    /**This is the setter to set appointment ID*/
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**This is the getter to get title of the appointment*/
    public String getTitle() {
        return title;
    }

    /**This is the setter to set the title of the appointment*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**This is the getter to get the description of the appointment*/
    public String getDescription() {
        return description;
    }

    /**This is the setter to set the description of the appointment*/
    public void setDescription(String description) {
        this.description = description;
    }

    /**This is the getter to get the location of the appointment*/
    public String getLocation() {
        return location;
    }

    /**This is the setter to set the location of the appointment*/
    public void setLocation(String location) {
        this.location = location;
    }

    /**This is the getter to get the type of the appointment*/
    public String getType() {
        return type;
    }

    /**This is the setter to set the type of the appointment*/
    public void setType(String type) {
        this.type = type;
    }

    /**This is the getter to get the start of the appointment*/
    public LocalDateTime getStart() {
        return start;
    }

    /**This is the setter to set the start of the appointment*/
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**This is a getter for getting the end of an appointment*/
    public LocalDateTime getEnd() {
        return end;
    }

    /**This is a setter for setting the end of an appointment*/
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**This is the getter to get the customer ID*/
    public String getCustomerId() {
        return customerId;
    }

    /**This is the setter to set the customer ID*/
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**This is the getter to egt the user ID*/
    public int getUserId() {
        return userId;
    }

    /**This is the setter to set the user ID*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**This is the getter to get the contact ID*/
    public String getContactId() {
        return contactId;
    }

    /**This is the setter to set the contact ID*/
    public void setContactId(String contactId) {
        this.contactId = contactId;
    }


    /**This is a constructor of all gathered information used for creating a new appointment object*/
    public Appointments(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String customerId, int userId, String contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**This is a constructor of all gathered information without the appointment ID used for updating an existing appointment object*/
    public Appointments(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, String customerId, int userId, String contactId) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**This is a constructor of the type and start for appointments*/
    public Appointments(String type, LocalDateTime start) {
        this.type = type;
        this.start = start;
    }

    /**This is a no args constructor*/
    public Appointments() {
    }

    /**This is a toString method to return the type as a string*/
    @Override
    public String toString() {
        return type;
    }
}
