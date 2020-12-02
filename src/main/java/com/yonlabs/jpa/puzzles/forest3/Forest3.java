package com.yonlabs.jpa.puzzles.forest3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Forest3 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    @OrderColumn
    private List<Tree3> trees = new ArrayList<Tree3>();

    public Long getId() {
        return id;
    }

    public List<Tree3> getTrees() {
        return Collections.unmodifiableList(trees);
    }

    public void plantTree(Tree3 tree) {
        trees.add(tree);
    }

}
