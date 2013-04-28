package com.livestormer.accounts.completeness.tasks;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
@RooJpaActiveRecord
public class Task {
    @NotNull
    @Size(max = 24)
    private String type;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 8000)
    private String description;

    @NotNull
    @Size(max = 8)
    @Column(unique = true)
    private String code;
    
    private int priority = 0;
    
    private Boolean isActive = true;
    
    public static List<Task> findTasksByType(String type) {
        return entityManager().createQuery("SELECT o FROM Task o where o.type = :type and o.isActive = :isActive order by o.priority desc", Task.class)
        		.setParameter("type", type)
        		.setParameter("isActive", Boolean.TRUE)
        		.getResultList();
    }
}
