package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest4.Forest4;
import com.yonlabs.jpa.puzzles.forest4.Tree4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest4Test extends BaseJPATest {

    private Forest4 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest4 forest = new Forest4();
            for (int i = 0; i < 10000; i++) {
                Tree4 tree = new Tree4("oak");
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
            Tree4 tree = new Tree4("willow");
            entityManager.persist(tree);
            Forest4 forest = entityManager.find(Forest4.class, existingForest.getId());
            forest.plantTree(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 2);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}