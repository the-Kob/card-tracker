package com.kob.service;

import com.kob.dto.CollectionDTO;
import com.kob.exception.ResourceNotFoundException;
import com.kob.repository.CollectionRepository;
import com.kob.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CollectionService {
    private final CollectionRepository repository;

    @Autowired
    public CollectionService(CollectionRepository repository) {
        this.repository = repository;
    }

    private static Collection mapToCollection(CollectionDTO collectionDTO) {
         Collection collection = Collection.builder()
                 .name(collectionDTO.getName())
                 .releaseDate(collectionDTO.getReleaseDate())
                 .series(collectionDTO.getSeries())
                 .complete(collectionDTO.isComplete())
                 .coverURL(collectionDTO.getCoverURL())
                 .build();

         return collection;
    }

    private static CollectionDTO mapToDTO(Collection collection) {
        CollectionDTO collectionDTO = CollectionDTO.builder()
                .name(collection.getName())
                .releaseDate(collection.getReleaseDate())
                .series(collection.getSeries())
                .complete(collection.isComplete())
                .coverURL(collection.getCoverURL())
                .build();

        return collectionDTO;
    }

    public List<Collection> getCollections() {
        List<Collection> result = new ArrayList<>();
        repository.findAll().forEach(result::add);

        return result;
    }

    public ResponseEntity<Collection> getCollection(Long id) throws ResourceNotFoundException {
        Collection collection = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection not found for this id :: " + id));

        return ResponseEntity.ok().body(collection);
    }

    public void addCollection(CollectionDTO collectionDTO) {
        /*
         * In case I want to make it so I only want collections with unique names.
         *
        Optional<Collection> collectionOptional = repository.findCollectionByName(collection.jsx.getName());

        if(collectionOptional.isPresent()) {
            throw new IllegalStateException("Name taken.");
        }
        */

        repository.save(mapToCollection(collectionDTO));
    }

    public Map<String, Boolean> deleteCollection(Long id) throws ResourceNotFoundException {
        Collection collection = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection not found for this id :: " + id));

        repository.delete(collection);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    public ResponseEntity<Collection> updateCollection(Long id, String name, LocalDate releaseDate, String series, boolean complete, String coverURL) throws ResourceNotFoundException {
        Collection collection = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection not found for this id :: " + id));

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

        final Collection updatedCollection = repository.save(collection);

        return ResponseEntity.ok(updatedCollection);
    }
}
