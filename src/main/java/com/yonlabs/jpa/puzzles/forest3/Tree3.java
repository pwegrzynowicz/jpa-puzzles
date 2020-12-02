package com.yonlabs.jpa.puzzles.forest3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tree3 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    protected Tree3() {
    }

    public Tree3(String species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

}
