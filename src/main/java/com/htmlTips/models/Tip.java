package com.htmlTips.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String exampleHTMLEscape;

    // constructor
    public Tip() {}
    public Tip(String title, String description, String exampleHTMLEscape) {
        this.title = title;
        this.description = description;
        this.exampleHTMLEscape = exampleHTMLEscape;
    }

    // getter and setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getExampleHTMLEscape() {return exampleHTMLEscape;}
    public void setExampleHTMLEscape(String exampleHTMLEscape) {this.exampleHTMLEscape = exampleHTMLEscape;}
}
