package ru.skillbox.janeskills.jdbc.service;

import java.util.List;
import ru.skillbox.janeskills.domain.SheetMetadata;
import ru.skillbox.janeskills.dto.SheetDataDto;

public interface SheetDataService {

    void save(SheetDataDto dto);

    void saveAll(List<SheetDataDto> dtoList);

    void update(SheetDataDto dto);

    SheetDataDto getData();

    void handleData(List<List<String>> sheetData, SheetMetadata metadata);

}
