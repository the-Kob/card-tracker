package com.kob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequest {
    private Long cardId;
    private String name;
    private int number;
    private String imgURL;
    private boolean haveIt;
    private int numberOfAdditionalCopies;
}
