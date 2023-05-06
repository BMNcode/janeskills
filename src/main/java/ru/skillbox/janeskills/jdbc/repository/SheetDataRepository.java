package ru.skillbox.janeskills.jdbc.repository;

import java.util.List;
import java.util.Optional;
import ru.skillbox.janeskills.domain.SheetData;

public interface SheetDataRepository extends BaseRepository<SheetData> {

    List<SheetData> findAllByMetadataId(Long id);

    Optional<SheetData> findByMetadataIdAndRowAndColumnCache(Long id, Integer row, Integer column);

}
