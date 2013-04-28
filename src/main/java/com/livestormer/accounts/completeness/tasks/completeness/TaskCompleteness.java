package com.livestormer.accounts.completeness.tasks.completeness;

import com.livestormer.accounts.completeness.tasks.Task;
import java.util.Date;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class TaskCompleteness {
    @ManyToOne
    private Task task;

    private Boolean isCompleted;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date completedOnDate;
    
    public static List<TaskCompleteness> findAllTaskCompletenesses() {
        return entityManager().createQuery("SELECT o FROM TaskCompleteness o order by o.id", TaskCompleteness.class).getResultList();
    }
}
