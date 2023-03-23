package com.kob.controller;

import com.kob.dto.CollectionDTO;
import com.kob.exception.ResourceNotFoundException;
import com.kob.model.Collection;
import com.kob.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin
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
    public ResponseEntity<Collection> getCollection(@PathVariable("collectionId") Long collectionId) throws ResourceNotFoundException {
        return collectionService.getCollection(collectionId);
    }

    @PostMapping
    public void addCollection(@RequestBody CollectionDTO collectionDTO) {
        collectionService.addCollection(collectionDTO);
    }

    @DeleteMapping("/{collectionId}")
    public Map<String, Boolean> deleteCollection(@PathVariable("collectionId") Long collectionId) throws ResourceNotFoundException {
        return collectionService.deleteCollection(collectionId);
    }

    @PutMapping("/{collectionId}")
    public ResponseEntity<Collection> updateCollection(@PathVariable("collectionId") Long collectionId,
                                                       @RequestParam(required = false) String name,
                                                       @RequestParam(required = false) LocalDate releaseDate,
                                                       @RequestParam(required = false) String series,
                                                       @RequestParam(required = false) boolean complete,
                                                       @RequestParam(required = false) String coverURL) throws ResourceNotFoundException {
        return collectionService.updateCollection(collectionId, name, releaseDate, series, complete, coverURL);
    }
}
