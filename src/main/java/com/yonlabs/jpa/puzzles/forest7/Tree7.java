package com.yonlabs.jpa.puzzles.forest7;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static java.util.Objects.requireNonNull;

@Entity
public class Tree7 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    @ManyToOne
    private Forest7 growsIn;

    protected Tree7() {
    }

    protected Tree7(String species, Forest7 forest) {
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
