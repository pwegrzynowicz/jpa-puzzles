package com.yonlabs.jpa.puzzles.forest5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static java.util.Objects.requireNonNull;

@Entity
public class Tree5 {

    @Id
    @GeneratedValue
    private Long id;

    private String species;

    @ManyToOne
    private Forest5 growsIn;

    protected Tree5() {
    }

    protected Tree5(String species, Forest5 forest) {
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
