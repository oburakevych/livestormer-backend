// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.owners.specialities;

import com.livestormer.accounts.owners.specialities.Speciality;

privileged aspect Speciality_Roo_JavaBean {
    
    public String Speciality.getName() {
        return this.name;
    }
    
    public void Speciality.setName(String name) {
        this.name = name;
    }
    
    public String Speciality.getDescription() {
        return this.description;
    }
    
    public void Speciality.setDescription(String description) {
        this.description = description;
    }
    
    public String Speciality.getType() {
        return this.type;
    }
    
    public void Speciality.setType(String type) {
        this.type = type;
    }
    
}