package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest5.Forest5;
import com.yonlabs.jpa.puzzles.forest5.Tree5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest5Test extends BaseJPATest {

    private Forest5 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest5 forest = new Forest5();
            for (int i = 0; i < 10000; i++) {
                Tree5 tree = forest.plantTree("oak");
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
            Forest5 forest = entityManager.find(Forest5.class, existingForest.getId());
            Tree5 tree = forest.plantTree("willow");
            entityManager.persist(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 1);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}