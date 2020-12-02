package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.forest6.Forest6;
import com.yonlabs.jpa.puzzles.forest6.Tree6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Forest6Test extends BaseJPATest {

    private Forest6 existingForest;

    @BeforeEach
    private void createForest() {
        existingForest = transactionTemplate.execute(status -> {
            Forest6 forest = new Forest6();
            for (int i = 0; i < 10000; i++) {
                Tree6 tree = forest.plantTree("oak");
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
            Forest6 forest = entityManager.find(Forest6.class, existingForest.getId());
            Tree6 tree = forest.plantTree("willow");
            entityManager.persist(tree);
            return forest;
        });

        System.out.println(sqlCounters);
        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 1);
        assert (sqlCounters.getUpdateCount() == 1);
    }

}