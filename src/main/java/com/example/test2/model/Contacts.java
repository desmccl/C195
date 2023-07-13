package com.example.test2.model;

/**This is the contacts model class*/
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /**This is a getter to get the contact ID*/
    public int getContactId() {
        return contactId;
    }

    /**This is the setter to set the contact ID*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**This is a getter to get the contact name*/
    public String getContactName() {
        return contactName;
    }

    /**This is a setter to set the contact name*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**This is a getter to get the contact email*/
    public String getEmail() {
        return email;
    }

    /**This is a setter to set the contact email*/
    public void setEmail(String email) {
        this.email = email;
    }

    /**This is the constructor of all gathered contact information used for contact objects*/
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**This is a toString method that returns the contact name as a string*/
    @Override
    public String toString() {
        return contactName;
    }
}
