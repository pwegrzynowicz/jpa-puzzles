package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest3.Forest3;
import com.yonlabs.jpa.puzzles.forest3.Tree3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest3Test extends BaseJPATest {

    private Forest3 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest3 forest = new Forest3();
            for (int i = 0; i < 10000; i++) {
                Tree3 tree = new Tree3("oak");
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
            Tree3 tree = new Tree3("willow");
            entityManager.persist(tree);
            Forest3 forest = entityManager.find(Forest3.class, existingForest.getId());
            forest.plantTree(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 2);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}