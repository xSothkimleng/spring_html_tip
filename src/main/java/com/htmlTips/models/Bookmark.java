package com.htmlTips.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long user_id;
    private Long tip_id;

    // constructor
    public Bookmark() {}
    public Bookmark(Long user_id, Long tip_id) {
        this.user_id = user_id;
        this.tip_id = tip_id;
    }

    // GETTER AND SETTER
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getUser_id() {return user_id;}
    public void setUser_id(Long user_id) {this.user_id = user_id;}
    public Long getTip_id() {return tip_id;}
    public void setTip_id(Long tip_id) {this.tip_id = tip_id;}
}
