package com.yonlabs.jpa.puzzles.hydra2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Entity
public class Hydra2 {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Head2> heads = new ArrayList<Head2>();

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public List<Head2> getHeads() {
        return Collections.unmodifiableList(heads);
    }

    public void setHeads(List<Head2> heads) {
        this.heads = heads;
    }

    public Hydra2 addHead(String description) {
        this.heads.add(new Head2(description));
        return this;
    }

    public Hydra2 cutHead(String description) {
        removeHead(description);
        regrowHeads(description);
        return this;
    }

    private void removeHead(String description) {
        int foundHead = findHeadIndex(description);
        heads.remove(foundHead);
    }

    private void regrowHeads(String description) {
        addHead(description);
        addHead(description);
        addHead(description);
    }

    private int findHeadIndex(String description) {
        return IntStream.range(0, heads.size())
                .filter(idx -> heads.get(idx).getDescription().equals(description))
                .findFirst()
                .orElseThrow();
    }

}