package com.livestormer.accounts.bookmarks;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@RooJson
public class Bookmark {

    @NotNull
    private Long accountId;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createdDate = new Date();

    @NotNull
    private Long objectId;

    @Enumerated(value = EnumType.STRING)
    private BookmarkType objectType;
    
    public static List<Bookmark> findAllBookmarksForAccount(Long accountId) {
        return entityManager().createQuery("SELECT o FROM Bookmark o where o.accountId = :accountId", Bookmark.class)
        		.setParameter("accountId", accountId)
        		.getResultList();
    }
}
