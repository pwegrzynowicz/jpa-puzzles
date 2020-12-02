package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest8.Forest8;
import com.yonlabs.jpa.puzzles.forest8.Tree8;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest8Test extends BaseJPATest {

    private Forest8 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest8 forest = new Forest8();
            for (int i = 0; i < 10000; i++) {
                Tree8 tree = forest.plantTree("oak");
                entityManager.persist(tree);
            }
            entityManager.persist(forest);
            return forest;
        });

        sqlCounters.reset();
    }

    @Test
    void testPlantOneTree() {
        transactionTemplate.execute(status -> {
            Forest8 forest = entityManager.find(Forest8.class, existingForest.getId());
            Tree8 tree = forest.plantTree("willow");
            entityManager.persist(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 1);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 1);
        assert (sqlCounters.getUpdateCount() == 0);
    }

    @Test
    void testRemoveForest() {
        transactionTemplate.execute(status -> {
            Forest8 forest = entityManager.find(Forest8.class, existingForest.getId());
            entityManager.remove(forest);
            return null;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 10001);
        assert (sqlCounters.getInsertCount() == 0);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}