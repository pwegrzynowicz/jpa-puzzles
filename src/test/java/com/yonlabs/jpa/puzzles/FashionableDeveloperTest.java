package com.yonlabs.jpa.puzzles;

import com.yonlabs.jpa.puzzles.dev.Developer;
import com.yonlabs.jpa.puzzles.dev.DeveloperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FashionableDeveloperTest extends BaseJPATest {

    @Autowired
    DeveloperRepository developerRepository;

    Developer existingDeveloper;

    @BeforeEach
    private void createDeveloper() {
        existingDeveloper = transactionTemplate.execute(status -> {
            Developer dev = new Developer();
            entityManager.persist(dev);
            return dev;
        });

        sqlCounters.reset();
    }

    @Test
    void testTryoutFlushes() {
        transactionTemplate.execute((status) -> {
            Developer dev = entityManager.find(Developer.class, existingDeveloper.getId());
            for (String technology : new String[]{"HTML5", "Scala", "Spring"}) {
                dev.setTryoutTechnology(technology);
                if (developerRepository.othersUse(technology, dev) > 0 && dev.likesTryoutTechnology()) {
                    dev.setMainTechnology(technology);
                }
            }
            dev.setTryoutTechnology(null);
            return dev;
        });

        assert(sqlCounters.getSelectCount() == 4);
        assert(sqlCounters.getUpdateCount() == 4);
        assert(sqlCounters.getInsertCount() == 0);
        assert(sqlCounters.getDeleteCount() == 0);
    }

    @Test
    void testTryoutNoFlushes() {
        transactionTemplate.execute((status) -> {
            Developer dev = entityManager.find(Developer.class, existingDeveloper.getId());
            for (String technology : new String[]{"HTML5", "Scala", "Spring"}) {
                dev.setTryoutTechnology(technology);
                if (dev.likesTryoutTechnology()) {
                    dev.setMainTechnology(technology);
                }
            }
            dev.setTryoutTechnology(null);
            return dev;
        });

        assert(sqlCounters.getSelectCount() == 1);
        assert(sqlCounters.getUpdateCount() == 0);
        assert(sqlCounters.getInsertCount() == 0);
        assert(sqlCounters.getDeleteCount() == 0);
    }

}
