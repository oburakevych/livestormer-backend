package com.livestormer.accounts.activities;

import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Activity {

    private Long accountId;

    private String type;

    @Size(max = 1024)
    private String description;

    @Size(max = 255)
    private String shortDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdOnDate = new Date();

    public static List<com.livestormer.accounts.activities.Activity> findActivitiesByAccountId(Long accountId) {
        return entityManager().createQuery("SELECT o FROM Activity o where o.accountId = :accountId order by o.createdOnDate desc", Activity.class)
        		.setParameter("accountId", accountId)
        		.setMaxResults(5)
        		.getResultList();
    }
    
    public static void addActivity(Long accountId, String shortDescription) {
		Activity act = new Activity();
		act.setAccountId(accountId);		
		act.setShortDescription(shortDescription);
		
		act.persist();
    }
}
