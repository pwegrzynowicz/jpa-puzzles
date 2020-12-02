package com.yonlabs.jpa.puzzles.forest1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tree1 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    protected Tree1() {
    }

    public Tree1(String species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

}
