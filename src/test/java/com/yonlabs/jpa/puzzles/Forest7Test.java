package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest7.Forest7;
import com.yonlabs.jpa.puzzles.forest7.Tree7;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest7Test extends BaseJPATest {

    private Forest7 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest7 forest = new Forest7();
            for (int i = 0; i < 10000; i++) {
                Tree7 tree = forest.plantTree("oak");
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
            Forest7 forest = entityManager.find(Forest7.class, existingForest.getId());
            Tree7 tree = forest.plantTree("willow");
            entityManager.persist(tree);
            return forest;
        });

        assert (sqlCounters.getSelectCount() == 1);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 1);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}