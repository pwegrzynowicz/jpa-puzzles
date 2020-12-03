package com.yonlabs.jpa.puzzles.dev;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    @Query(value = "select count(*) from developer where main_technology = :technology and id <> :developer",
        nativeQuery = true)
    long othersUse(String technology, Developer developer);

}
