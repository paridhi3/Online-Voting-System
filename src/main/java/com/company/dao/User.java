package com.company.dao;

import java.time.LocalDate; // Import LocalDate for date handling

public class User {
    private int voter_id;
    private String name;
    private String password;
    private String dob; 

    // Getter and Setter for voter_id
    public int getVoterID() {
        return voter_id;
    }
    public void setVoterID(int voter_id) {
        this.voter_id = voter_id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for dob
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
}
