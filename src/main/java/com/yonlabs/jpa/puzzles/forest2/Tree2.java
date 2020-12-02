package com.yonlabs.jpa.puzzles.forest2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tree2 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    protected Tree2() {
    }

    public Tree2(String species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

}
