package com.yonlabs.jpa.puzzles.hydra2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static java.util.Objects.requireNonNull;

@Entity
public class Head2 {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    protected Head2() {
    }

    public Head2(String description) {
        setDescription(description);
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = requireNonNull(description);
    }

}
