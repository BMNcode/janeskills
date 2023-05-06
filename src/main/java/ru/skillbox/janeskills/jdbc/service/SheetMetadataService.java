package ru.skillbox.janeskills.jdbc.service;

import com.google.api.services.sheets.v4.model.SheetProperties;
import java.util.List;
import ru.skillbox.janeskills.domain.SheetMetadata;
import ru.skillbox.janeskills.dto.SheetMetadataDto;

public interface SheetMetadataService {

    void save(SheetMetadataDto dto);

    void saveAll(List<SheetMetadataDto> dtoList);

    void createOrUpdate(List<SheetProperties> properties);

    void update(SheetMetadataDto dto);

    List<SheetMetadata> findAll();

    SheetMetadata getData();

}
