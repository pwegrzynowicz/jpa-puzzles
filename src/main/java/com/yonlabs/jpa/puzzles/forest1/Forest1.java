package com.yonlabs.jpa.puzzles.forest1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Entity
public class Forest1 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Collection<Tree1> trees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public Collection<Tree1> getTrees() {
        return Collections.unmodifiableCollection(trees);
    }

    public void plantTree(Tree1 tree) {
        trees.add(tree);
    }

}
