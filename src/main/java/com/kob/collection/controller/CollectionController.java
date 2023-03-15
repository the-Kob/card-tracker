package com.kob.collection.controller;

import com.kob.collection.model.Collection;
import com.kob.collection.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/collections")
public class CollectionController {
    private final CollectionService collectionService;

    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping
    public List<Collection> getCollections() {
        return collectionService.getCollections();
    }

    @GetMapping("/{collectionId}")
    public Collection getCollection(@PathVariable("collectionId") Integer collectionId) {
        return collectionService.getCollection(collectionId);
    }

    @PostMapping
    public void addCollection(@RequestBody Collection collection) {
        collectionService.addCollection(collection);
    }

    @DeleteMapping("/{collectionId}")
    public void deleteCollection(@PathVariable("collectionId") Integer collectionId) {
        collectionService.deleteCollection(collectionId);
    }

    @PutMapping("/{collectionId}")
    public void updateCollection(@PathVariable("collectionId") Integer collectionId,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) LocalDate releaseDate,
                                 @RequestParam(required = false) String series,
                                 @RequestParam(required = false) boolean complete,
                                 @RequestParam(required = false) String coverURL) {
        collectionService.updateCollection(collectionId, name, releaseDate, series, complete, coverURL);
    }
}
