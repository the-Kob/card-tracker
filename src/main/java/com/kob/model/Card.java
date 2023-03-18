package com.kob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Card {
    @Id
    @SequenceGenerator(
            name = "card_id_sequence",
            sequenceName = "card_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_id_sequence"
    )
    private Long cardId;
    private String name;
    private int number;
    private String imgURL;
    private boolean haveIt;
    private int numberOfAdditionalCopies;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collectionId", referencedColumnName = "collectionId")
    private Collection collection;
}
