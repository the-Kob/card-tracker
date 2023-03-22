package com.kob.controller;

import com.kob.dto.CardRequest;
import com.kob.exception.ResourceNotFoundException;
import com.kob.model.Card;
import com.kob.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1/collections/{collectionId}/cards")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCards(@PathVariable Long collectionId) throws ResourceNotFoundException {
        return cardService.getCards(collectionId);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCard(@PathVariable("collectionId") Long collectionId, @PathVariable("cardId") Long cardId) throws ResourceNotFoundException {
        return cardService.getCard(collectionId, cardId);
    }

    @PostMapping
    public void addCard(@PathVariable("collectionId") Long collectionId, @RequestBody CardRequest card) throws ResourceNotFoundException { cardService.addCard(collectionId, card);}

    @DeleteMapping("/{cardId}")
    public Map<String, Boolean> deleteCard(@PathVariable("collectionId") Long collectionId, @PathVariable("cardId") Long cardId) throws ResourceNotFoundException {
        return cardService.deleteCard(collectionId, cardId);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable("collectionId") Long collectionId,
                                           @PathVariable("cardId") Long cardId,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) int number,
                                           @RequestParam(required = false) String imgURL,
                                           @RequestParam(required = false) boolean haveIt,
                                           @RequestParam(required = false) int numberOfAdditionalCopies) throws ResourceNotFoundException {
        return cardService.updateCard(collectionId, cardId, name, number, imgURL, haveIt, numberOfAdditionalCopies);
    }
}
