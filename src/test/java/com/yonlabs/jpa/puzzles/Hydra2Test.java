package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.hydra2.Head2;
import com.yonlabs.jpa.puzzles.hydra2.Hydra2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Hydra2Test extends BaseJPATest {

    private Hydra2 existingHydra;

    @BeforeEach
    private void createHydra() {
        existingHydra = transactionTemplate.execute(status -> {
            Hydra2 hydra = new Hydra2();
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
                entityManager.find(Hydra2.class, existingHydra.getId())
        );

        assert (sqlCounters.getSelectCount() == 1);
        assert (sqlCounters.getDeleteCount() == 0);
        assert (sqlCounters.getInsertCount() == 0);
        assert (sqlCounters.getUpdateCount() == 0);
    }

    @Test
    void testUpdateHeads() {
        transactionTemplate.execute(status -> {
            Hydra2 hydra = entityManager.find(Hydra2.class, existingHydra.getId());
            List<Head2> sameHeads = new ArrayList<>(hydra.getHeads());
            hydra.setHeads(sameHeads);
            return hydra;
        });

        assert (sqlCounters.getSelectCount() == 2);
        assert (sqlCounters.getDeleteCount() == 1);
        assert (sqlCounters.getInsertCount() == 3);
        assert (sqlCounters.getUpdateCount() == 0);
    }

}
