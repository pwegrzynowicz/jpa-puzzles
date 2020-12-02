package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest1.Forest1;
import com.yonlabs.jpa.puzzles.forest1.Tree1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest1Test extends BaseJPATest {

    private Forest1 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest1 forest = new Forest1();
            for (int i = 0; i < 10000; i++) {
                Tree1 tree = new Tree1("oak");
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
            Tree1 tree = new Tree1("willow");
            entityManager.persist(tree);
            Forest1 forest = entityManager.find(Forest1.class, existingForest.getId());
            forest.plantTree(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 1);
        assert (sqlCounters.getInsertCount() == 10002);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}