// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.completeness.tasks.completeness;

import com.livestormer.accounts.completeness.tasks.Task;
import com.livestormer.accounts.completeness.tasks.completeness.TaskCompleteness;
import java.util.Date;

privileged aspect TaskCompleteness_Roo_JavaBean {
    
    public Task TaskCompleteness.getTask() {
        return this.task;
    }
    
    public void TaskCompleteness.setTask(Task task) {
        this.task = task;
    }
    
    public Boolean TaskCompleteness.getIsCompleted() {
        return this.isCompleted;
    }
    
    public void TaskCompleteness.setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    
    public Date TaskCompleteness.getCompletedOnDate() {
        return this.completedOnDate;
    }
    
    public void TaskCompleteness.setCompletedOnDate(Date completedOnDate) {
        this.completedOnDate = completedOnDate;
    }
    
}
