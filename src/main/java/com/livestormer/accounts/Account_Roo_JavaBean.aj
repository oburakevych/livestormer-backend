// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts;

import com.livestormer.accounts.Account;
import com.livestormer.accounts.owners.Owner;
import java.util.Date;

privileged aspect Account_Roo_JavaBean {
    
    public String Account.getType() {
        return this.type;
    }
    
    public void Account.setType(String type) {
        this.type = type;
    }
    
    public Date Account.getCreated() {
        return this.created;
    }
    
    public void Account.setCreated(Date created) {
        this.created = created;
    }
    
    public Owner Account.getOwner() {
        return this.owner;
    }
    
    public void Account.setOwner(Owner owner) {
        this.owner = owner;
    }
    
}
