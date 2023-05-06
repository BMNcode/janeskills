package ru.skillbox.janeskills.jdbc.service.impl;

import com.google.api.services.sheets.v4.model.SheetProperties;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import ru.skillbox.janeskills.domain.SheetMetadata;
import ru.skillbox.janeskills.dto.SheetMetadataDto;
import ru.skillbox.janeskills.jdbc.repository.SheetMetadataRepository;
import ru.skillbox.janeskills.jdbc.service.SheetMetadataService;
import ru.skillbox.janeskills.mapper.SheetMetadataMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class SheetMetadataServiceImpl implements SheetMetadataService {

    private final SheetMetadataMapper mapper;
    private final SheetMetadataRepository repository;

    @Override
    public void save(SheetMetadataDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    @Override
    public void saveAll(List<SheetMetadataDto> dtoList) {

    }

    @Override
    public void createOrUpdate(List<SheetProperties> properties) {
        var sheetProperties = properties.stream()
                .map(props -> mapper.toDto(props.getTitle(), props.getGridProperties().getRowCount()))
                .toList();

        var metadata = repository.findByTitles(sheetProperties.stream()
                        .map(SheetMetadataDto::title)
                        .toList()).stream()
                .collect(Collectors.toMap(SheetMetadata::getTitle, data -> data));

        Stream.ofNullable(sheetProperties)
                .flatMap(Collection::stream)
                .collect(Collectors.partitioningBy(dto -> metadata.containsKey(dto.title())))
                .forEach((key, value) -> {
                    if (BooleanUtils.isTrue(key)) {
                        repository.updateAll(value.stream()
                                .map(data -> mapper.toEntity(metadata.get(data.title()).getId(), data))
                                .toList());
                    } else {
                        repository.saveAll(value.stream()
                                .map(mapper::toEntity)
                                .toList());
                    }
                });
    }

    @Override
    public List<SheetMetadata> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(SheetMetadataDto dto) {

    }

    @Override
    public SheetMetadata getData() {
        return null;
    }

}
