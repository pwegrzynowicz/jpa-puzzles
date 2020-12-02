package com.yonlabs.jpa.puzzles.hydra1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Hydra1 {

    private Long id;

    private List<Head1> heads = new ArrayList<Head1>();

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Head1> getHeads() {
        return Collections.unmodifiableList(heads);
    }

    public void setHeads(List<Head1> heads) {
        this.heads = heads;
    }

    public Hydra1 addHead(String description) {
        this.heads.add(new Head1(description));
        return this;
    }

}