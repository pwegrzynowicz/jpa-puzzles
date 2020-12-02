package com.yonlabs.jpa.puzzles.forest6;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Forest6 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "growsIn")
    @OrderColumn
    private List<Tree6> trees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public List<Tree6> getTrees() {
        return Collections.unmodifiableList(trees);
    }

    public Tree6 plantTree(String species) {
        Tree6 newTree = new Tree6(species, this);
        trees.add(newTree);
        return newTree;
    }

}
