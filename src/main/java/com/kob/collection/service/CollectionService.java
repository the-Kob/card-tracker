package com.kob.collection.service;

import com.kob.collection.CollectionRepository;
import com.kob.collection.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class CollectionService {
    private final CollectionRepository repository;

    @Autowired
    public CollectionService(CollectionRepository repository) {
        this.repository = repository;
    }

    public List<Collection> getCollections() {
        return repository.findAll();
    }

    public Collection getCollection(Integer id) {
        Collection collection = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Collection with id " + id + " does not exist."));

        return collection;
    }

    public void addCollection(Collection collection) {
        /*
         * In case I want to make it so I only want collections with unique names.
         *
        Optional<Collection> collectionOptional = repository.findCollectionByName(collection.getName());

        if(collectionOptional.isPresent()) {
            throw new IllegalStateException("Name taken.");
        }
        */

        repository.save(collection);
    }

    public void deleteCollection(Integer id) {
        boolean exists = repository.existsById(id);

        if(!exists) {
            throw new IllegalStateException("Collection with id " + id + " does not exist.");
        }

        repository.deleteById(id);
    }

    public void updateCollection(Integer id, String name, LocalDate releaseDate, String series, boolean complete, String coverURL) {
        Collection collection = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Collection with id " + id + " does not exist."));

        if(name != null && name.length() > 0 && !Objects.equals(collection.getName(), name)) {
            collection.setName(name);
        }

        if(releaseDate != null && !Objects.equals(collection.getReleaseDate(), releaseDate)) {
            collection.setReleaseDate(releaseDate);
        }

        if(series != null && series.length() > 0 && !Objects.equals(collection.getSeries(), series)) {
            collection.setSeries(series);
        }

        if(!Objects.equals(collection.isComplete(), complete)) {
            collection.setComplete(complete);
        }

        if(coverURL != null && coverURL.length() > 0 && !Objects.equals(collection.getCoverURL(), coverURL)) {
            collection.setCoverURL(coverURL);
        }

        repository.save(collection);
    }
}
