package com.yonlabs.jpa.puzzles.forest5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Forest5 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "growsIn")
    private Set<Tree5> trees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Set<Tree5> getTrees() {
        return Collections.unmodifiableSet(trees);
    }

    public Tree5 plantTree(String species) {
        Tree5 newTree = new Tree5(species, this);
        trees.add(newTree);
        return newTree;
    }

}
