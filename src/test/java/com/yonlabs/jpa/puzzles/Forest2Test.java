package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest2.Forest2;
import com.yonlabs.jpa.puzzles.forest2.Tree2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest2Test extends BaseJPATest {

    private Forest2 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest2 forest = new Forest2();
            for (int i = 0; i < 10000; i++) {
                Tree2 tree = new Tree2("oak");
                entityManager.persist(tree);
                forest.plantTree(tree);
            }
            entityManager.persist(forest);
            return forest;
        });

        sqlCounters.reset();
    }

    @Test
    void testPlantOneTree() {
        transactionTemplate.execute(status -> {
            Tree2 tree = new Tree2("willow");
            entityManager.persist(tree);
            Forest2 forest = entityManager.find(Forest2.class, existingForest.getId());
            forest.plantTree(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 1);
        assert (sqlCounters.getInsertCount() == 10002);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}