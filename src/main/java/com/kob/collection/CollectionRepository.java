package com.kob.collection;

import com.kob.collection.model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    @Query("select c from Collection c where c.name = ?1")
    Optional<Collection> findCollectionByName(String name);
}
