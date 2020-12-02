package com.yonlabs.jpa.puzzles.forest8;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static java.util.Objects.requireNonNull;

@Entity
public class Tree8 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    @ManyToOne
    private Forest8 growsIn;

    protected Tree8() {
    }

    protected Tree8(String species, Forest8 forest) {
        this.species = requireNonNull(species);
        this.growsIn = requireNonNull(forest);
    }

    public Long getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

}
