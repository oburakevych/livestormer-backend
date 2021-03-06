// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.completeness.tasks.completeness;

import com.livestormer.accounts.completeness.tasks.completeness.TaskCompleteness;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TaskCompleteness_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TaskCompleteness.entityManager;
    
    public static final EntityManager TaskCompleteness.entityManager() {
        EntityManager em = new TaskCompleteness().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TaskCompleteness.countTaskCompletenesses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TaskCompleteness o", Long.class).getSingleResult();
    }
    
    public static TaskCompleteness TaskCompleteness.findTaskCompleteness(Long id) {
        if (id == null) return null;
        return entityManager().find(TaskCompleteness.class, id);
    }
    
    public static List<TaskCompleteness> TaskCompleteness.findTaskCompletenessEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TaskCompleteness o", TaskCompleteness.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TaskCompleteness.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TaskCompleteness.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TaskCompleteness attached = TaskCompleteness.findTaskCompleteness(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TaskCompleteness.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TaskCompleteness.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TaskCompleteness TaskCompleteness.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TaskCompleteness merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
