package com.yonlabs.jpa.puzzles.forest7;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Forest7 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "growsIn")
    private List<Tree7> trees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Collection<Tree7> getTrees() {
        return Collections.unmodifiableCollection(trees);
    }

    public Tree7 plantTree(String species) {
        Tree7 newTree = new Tree7(species, this);
        trees.add(newTree);
        return newTree;
    }

}
