// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.owners;

import com.livestormer.accounts.owners.Owner;
import com.livestormer.accounts.owners.specialities.Speciality;
import java.util.Set;

privileged aspect Owner_Roo_JavaBean {
    
    public String Owner.getName() {
        return this.name;
    }
    
    public void Owner.setName(String name) {
        this.name = name;
    }
    
    public String Owner.getUsername() {
        return this.username;
    }
    
    public void Owner.setUsername(String username) {
        this.username = username;
    }
    
    public String Owner.getPassword() {
        return this.password;
    }
    
    public void Owner.setPassword(String password) {
        this.password = password;
    }
    
    public String Owner.getEmail() {
        return this.email;
    }
    
    public void Owner.setEmail(String email) {
        this.email = email;
    }
    
    public String Owner.getCountry() {
        return this.country;
    }
    
    public void Owner.setCountry(String country) {
        this.country = country;
    }
    
    public String Owner.getCity() {
        return this.city;
    }
    
    public void Owner.setCity(String city) {
        this.city = city;
    }
    
    public Set<Speciality> Owner.getSpecialities() {
        return this.specialities;
    }
    
    public void Owner.setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
    
}