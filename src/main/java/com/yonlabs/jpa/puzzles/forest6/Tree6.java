package com.yonlabs.jpa.puzzles.forest6;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static java.util.Objects.requireNonNull;

@Entity
public class Tree6 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    @ManyToOne
    private Forest6 growsIn;

    protected Tree6() {
    }

    protected Tree6(String species, Forest6 forest) {
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
