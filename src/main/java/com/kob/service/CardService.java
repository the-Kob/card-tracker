package com.kob.service;

import com.kob.dto.CardRequest;
import com.kob.exception.ResourceNotFoundException;
import com.kob.model.Card;
import com.kob.model.Collection;
import com.kob.repository.CardRepository;
import com.kob.repository.CollectionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Transactional
public class CardService {
    private final CardRepository cardRepository;
    private final CollectionRepository collectionRepository;

    private Card mapToCard(Long collectionId, CardRequest cardRequest) throws ResourceNotFoundException {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Collection not found for this id :: " + collectionId));
        Card newCard = Card.builder()
                .cardId(cardRequest.getCardId())
                .name(cardRequest.getName())
                .number(cardRequest.getNumber())
                .imgURL(cardRequest.getImgURL())
                .haveIt(cardRequest.isHaveIt())
                .numberOfAdditionalCopies(cardRequest.getNumberOfAdditionalCopies())
                .collection(collection)
                .build();

        return newCard;
    }

    public List<Card> getCards(Long collectionId) throws ResourceNotFoundException {
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new ResourceNotFoundException("Collection not found for this id :: " + collectionId));

        List<Card> result = new ArrayList<>();
        cardRepository.findAllByCollection(collection).forEach(result::add);

        return result;
    }

    public ResponseEntity<Card> getCard(Long collectionId, Long cardId) throws ResourceNotFoundException {
        List<Card> cards = getCards(collectionId);
        Card card = cards.stream().filter(c -> cardId.equals(c.getCardId())).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id :: " + cardId));

        return ResponseEntity.ok().body(card);
    }

    public void addCard(Long collectionId, CardRequest card) throws ResourceNotFoundException {
        cardRepository.save(mapToCard(collectionId, card));
    }

    public Map<String, Boolean> deleteCard(Long collectionId, Long cardId) throws ResourceNotFoundException {
        List<Card> cards = getCards(collectionId);
        Card card = cards.stream().filter(c -> cardId.equals(c.getCardId())).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id :: " + cardId));

        cardRepository.delete(card);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    public ResponseEntity<Card> updateCard(Long collectionId, Long cardId, String name, int number, String imgURL, boolean haveIt, int numberOfAdditionalCopies) throws ResourceNotFoundException {
        List<Card> cards = getCards(collectionId);
        Card card = cards.stream().filter(c -> cardId.equals(c.getCardId())).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Card not found for this id :: " + cardId));

        if(name != null && name.length() > 0 && !Objects.equals(card.getName(), name)) {
            card.setName(name);
        }

        if(number <= 0 && !Objects.equals(card.getNumber(), number)) {
            card.setNumber(number);
        }

        if(imgURL != null && imgURL.length() > 0 && !Objects.equals(card.getImgURL(), imgURL)) {
            card.setImgURL(imgURL);
        }

        if(!Objects.equals(card.isHaveIt(), haveIt)) {
            card.setHaveIt(haveIt);
        }

        if(numberOfAdditionalCopies <= 0 && !Objects.equals(card.getNumberOfAdditionalCopies(), numberOfAdditionalCopies)) {
            card.setNumberOfAdditionalCopies(numberOfAdditionalCopies);
        }

        final Card updatedCard = cardRepository.save(card);

        return ResponseEntity.ok(updatedCard);
    }
}
