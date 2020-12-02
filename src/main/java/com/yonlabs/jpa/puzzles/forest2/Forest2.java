package com.yonlabs.jpa.puzzles.forest2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Forest2 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Tree2> trees = new ArrayList<Tree2>();

    public Long getId() {
        return id;
    }

    public List<Tree2> getTrees() {
        return Collections.unmodifiableList(trees);
    }

    public void plantTree(Tree2 tree) {
        trees.add(tree);
    }

}
