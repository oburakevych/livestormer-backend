package com.livestormer.accounts.owners;

import com.livestormer.accounts.owners.specialities.Speciality;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
@RooJpaActiveRecord(finders = { "findOwnersByUsernameEquals" })
public class Owner {
    private String name;

    @NotNull
    @Column(unique = true)
    @Size(max = 255)
    private String username;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String email;

    private String country;

    private String city;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Speciality> specialities;

    public static com.livestormer.accounts.owners.Owner getOwner(String username, String password) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        if (password == null || password.length() == 0) throw new IllegalArgumentException("The password argument is required");
        
        return findOwner(username.toLowerCase(), password).getSingleResult();
    }

    public static TypedQuery<com.livestormer.accounts.owners.Owner> findOwner(String username, String password) {
        EntityManager em = Owner.entityManager();
        TypedQuery<Owner> q = em.createQuery("SELECT o FROM Owner AS o WHERE o.username = :username and o.password = :password", Owner.class);
        q.setParameter("username", username);
        q.setParameter("password", password);
        return q;
    }
}
