package com.yonlabs.jpa.puzzles.forest4;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Forest4 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @OrderColumn
    private Set<Tree4> trees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Set<Tree4> getTrees() {
        return Collections.unmodifiableSet(trees);
    }

    public void plantTree(Tree4 tree) {
        trees.add(tree);
    }

}
