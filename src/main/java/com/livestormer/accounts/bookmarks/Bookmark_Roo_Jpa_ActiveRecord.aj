// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.bookmarks;

import com.livestormer.accounts.bookmarks.Bookmark;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Bookmark_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Bookmark.entityManager;
    
    public static final EntityManager Bookmark.entityManager() {
        EntityManager em = new Bookmark().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Bookmark.countBookmarks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Bookmark o", Long.class).getSingleResult();
    }
    
    public static List<Bookmark> Bookmark.findAllBookmarks() {
        return entityManager().createQuery("SELECT o FROM Bookmark o", Bookmark.class).getResultList();
    }
    
    public static Bookmark Bookmark.findBookmark(Long id) {
        if (id == null) return null;
        return entityManager().find(Bookmark.class, id);
    }
    
    public static List<Bookmark> Bookmark.findBookmarkEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Bookmark o", Bookmark.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Bookmark.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Bookmark.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Bookmark attached = Bookmark.findBookmark(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Bookmark.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Bookmark.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Bookmark Bookmark.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Bookmark merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
