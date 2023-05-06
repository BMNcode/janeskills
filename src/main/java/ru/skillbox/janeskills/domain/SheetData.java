package ru.skillbox.janeskills.domain;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SheetData {

    private Long id;
    private Long metadataId;
    private Integer row;
    private Integer column;
    private String data;
    private OffsetDateTime updatedAt;
}
