package com.example.test2.model;

public class FirstLevelDivisions {
    private String division;

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    private int divisionId;
    private int countryId;
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setDivision(String division)
    {
        this.division = division;
    }

    public String getDivision() {
        return division;
    }

    public FirstLevelDivisions(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return division;
    }
}








