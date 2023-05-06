package ru.skillbox.janeskills.mapper;

import java.time.OffsetDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.janeskills.domain.SheetData;
import ru.skillbox.janeskills.dto.SheetDataDto;

@Mapper(componentModel = "spring", imports = OffsetDateTime.class)
public interface SheetDataMapper {

    SheetData toEntity(SheetDataDto dto);

    @Mapping(target = "updatedAt", expression = "java(OffsetDateTime.now())")
    SheetData toEntity(Long metadataId, Integer row, Integer column, String data);

    @Mapping(target = "updatedAt", expression = "java(OffsetDateTime.now())")
    SheetData toEntity(Long id, Long metadataId, Integer row, Integer column, String data);

}
