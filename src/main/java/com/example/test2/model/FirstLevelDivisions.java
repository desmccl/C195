package com.example.test2.model;
/**This is the First Level Divisions model class*/
public class FirstLevelDivisions {
    /**These are the variables for division, division id, and country id*/
    private String division;
    private int divisionId;
    private int countryId;
    /**This is the getter for division id*/
    public int getDivisionId() {
        return divisionId;
    }
    /**This is the setter for division id*/
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**This is the getter for country id*/
    public int getCountryId() {
        return countryId;
    }
    /**This is the setter for country id*/
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**This is the setter for division*/
    public void setDivision(String division)
    {
        this.division = division;
    }
    /**This is the getter for division*/
    public String getDivision() {
        return division;
    }
/**This is the constructor*/
    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }
/**This is the toString method to return division as a string*/
    @Override
    public String toString() {
        return division;
    }
}








