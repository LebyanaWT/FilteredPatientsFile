
package com.reverside.filterpatientsdata.pojo;

import java.util.Comparator;

/**
 *
 * @author William
 */
public class Patient {
    private String name;
    private String gender;
    public String dob;

    public Patient() {
    }

    public Patient(String name, String gender, String dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    } 
    
     public Comparator<Patient> SortByName = new Comparator<Patient>(){
        @Override
        public int compare(Patient patientOneObj, Patient patientTwoObj) {
            return patientOneObj.getName().compareTo(patientTwoObj.getName());
        }
    };
}
