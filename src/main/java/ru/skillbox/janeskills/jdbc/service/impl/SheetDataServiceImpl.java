package ru.skillbox.janeskills.jdbc.service.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.skillbox.janeskills.domain.SheetMetadata;
import ru.skillbox.janeskills.dto.SheetDataDto;
import ru.skillbox.janeskills.jdbc.repository.SheetDataRepository;
import ru.skillbox.janeskills.jdbc.service.SheetDataService;
import ru.skillbox.janeskills.mapper.SheetDataMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class SheetDataServiceImpl implements SheetDataService {

    private final SheetDataMapper mapper;
    private final SheetDataRepository repository;

    @Override
    public void save(SheetDataDto dto) {
        repository.save(mapper.toEntity(dto));
    }

    @Override
    public void saveAll(List<SheetDataDto> dtoList) {

    }

    @Override
    public void update(SheetDataDto dto) {

    }

    @Override
    public SheetDataDto getData() {
        return null;
    }

    @Async
    @Override
    public void handleData(List<List<String>> sheetData, SheetMetadata metadata) {
        for (int i = 0; i < sheetData.size(); i++) {
            for (int j = 0; j < sheetData.get(i).size(); j++) {
                var optData = repository.findByMetadataIdAndRowAndColumnCache(metadata.getId(), i, j);
                if (optData.isPresent()) {
                    if (!Objects.equals(optData.get().getData(), sheetData.get(i).get(j))) {
                        repository.update(mapper.toEntity(
                                optData.get().getId(), metadata.getId(), i, j, sheetData.get(i).get(j))
                        );
                    }
                } else {
                    repository.save(mapper.toEntity(
                            metadata.getId(), i, j, sheetData.get(i).get(j))
                    );
                }
            }
        }
    }
}
