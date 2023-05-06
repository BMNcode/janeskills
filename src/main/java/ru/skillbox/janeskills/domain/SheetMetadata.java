package ru.skillbox.janeskills.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SheetMetadata {

    private Long id;
    private String title;
    private Integer rowCount;

}
