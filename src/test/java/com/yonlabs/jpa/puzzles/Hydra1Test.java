package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.hydra1.Hydra1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Hydra1Test extends BaseJPATest {

    private Hydra1 existingHydra;

    @BeforeEach
    private void createHydra() {
        existingHydra = transactionTemplate.execute(status -> {
            Hydra1 hydra = new Hydra1();
            hydra.addHead("angry")
                    .addHead("big")
                    .addHead("cute");
            entityManager.persist(hydra);
            return hydra;
        });

        sqlCounters.reset();
    }

    @Test
    void testFindHydra() {
        transactionTemplate.execute(status ->
            entityManager.find(Hydra1.class, existingHydra.getId())
        );

        assert(sqlCounters.getSelectCount() == 2);
        assert(sqlCounters.getDeleteCount() == 1);
        assert(sqlCounters.getInsertCount() == 3);
        assert(sqlCounters.getUpdateCount() == 0);
    }
}
