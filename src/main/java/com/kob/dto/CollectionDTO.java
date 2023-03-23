package com.kob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {
    private String name;
    private LocalDate releaseDate;
    private String series;
    private boolean complete;
    private String coverURL;
}
