// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.completeness.tasks.completeness;

import com.livestormer.accounts.completeness.tasks.completeness.TaskCompleteness;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect TaskCompleteness_Roo_Jpa_Entity {
    
    declare @type: TaskCompleteness: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long TaskCompleteness.id;
    
    @Version
    @Column(name = "version")
    private Integer TaskCompleteness.version;
    
    public Long TaskCompleteness.getId() {
        return this.id;
    }
    
    public void TaskCompleteness.setId(Long id) {
        this.id = id;
    }
    
    public Integer TaskCompleteness.getVersion() {
        return this.version;
    }
    
    public void TaskCompleteness.setVersion(Integer version) {
        this.version = version;
    }
    
}
