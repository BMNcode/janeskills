package ru.skillbox.janeskills.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.janeskills.domain.SheetMetadata;
import ru.skillbox.janeskills.dto.SheetMetadataDto;

@Mapper(componentModel = "spring")
public interface SheetMetadataMapper {

    SheetMetadata toEntity(SheetMetadataDto dto);

    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "rowCount", source = "dto.rowCount")
    SheetMetadata toEntity(Long id, SheetMetadataDto dto);

    SheetMetadataDto toDto(String title, Integer rowCount);

}
