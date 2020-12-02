package com.yonlabs.jpa.puzzles.forest8;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Forest8 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "growsIn")
    private Collection<Tree8> trees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Collection<Tree8> getTrees() {
        return Collections.unmodifiableCollection(trees);
    }

    public Tree8 plantTree(String species) {
        Tree8 newTree = new Tree8(species, this);
        trees.add(newTree);
        return newTree;
    }

}
