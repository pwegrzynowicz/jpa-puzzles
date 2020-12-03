package com.yonlabs.jpa.puzzles.dev;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Developer {

    @Id @GeneratedValue
    private Long id;

    private String mainTechnology;

    private String tryoutTechnology;

    public Developer() {}

    public Long getId() {
        return id;
    }

    public String getMainTechnology() {
        return mainTechnology;
    }

    public void setMainTechnology(String mainTechnology) {
        this.mainTechnology = mainTechnology;
    }

    public String getTryoutTechnology() {
        return tryoutTechnology;
    }

    public void setTryoutTechnology(String tryoutTechnology) {
        this.tryoutTechnology = tryoutTechnology;
    }

    public boolean likesTryoutTechnology() {
        return "hibernate".equalsIgnoreCase(tryoutTechnology);
    }

}
