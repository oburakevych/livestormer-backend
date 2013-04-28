package com.livestormer.accounts.completeness;

import com.livestormer.accounts.Account;
import com.livestormer.accounts.completeness.tasks.completeness.TaskCompleteness;

import flexjson.JSONSerializer;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RooJavaBean
@RooToString
@RooJson
@RooJpaActiveRecord(finders = { "findAccountCompletenessesByAccount" })
public class AccountCompleteness {

    @OneToOne
    private Account account;

    private int rate;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<TaskCompleteness> taskCompletenesses = new HashSet<TaskCompleteness>();
    
    public static TypedQuery<AccountCompleteness> findAccountCompletenessesByAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("The account argument is required");
        EntityManager em = AccountCompleteness.entityManager();
        TypedQuery<AccountCompleteness> q = em.createQuery("SELECT o FROM AccountCompleteness AS o WHERE o.account = :account", AccountCompleteness.class);
        q.setParameter("account", account);
        return q;
    }
    
    public String toJson() {
        return new JSONSerializer().exclude("*.class").include("taskCompletenesses").serialize(this);
    }
}
