package com.yonlabs.jpa.puzzles.forest4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tree4 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    protected Tree4() {
    }

    public Tree4(String species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

}
