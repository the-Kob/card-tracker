package com.kob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Collection {
    @Id
    @SequenceGenerator(
            name = "collection_id_sequence",
            sequenceName = "collection_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collection_id_sequence"
    )
    private Long collectionId;
    private String name;
    private LocalDate releaseDate;
    private String series;
    private boolean complete;
    private String coverURL;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Card> cards;
}
