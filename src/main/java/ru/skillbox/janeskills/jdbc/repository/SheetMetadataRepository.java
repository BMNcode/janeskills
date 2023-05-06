package ru.skillbox.janeskills.jdbc.repository;

import java.util.List;
import ru.skillbox.janeskills.domain.SheetMetadata;

public interface SheetMetadataRepository extends BaseRepository<SheetMetadata> {

    List<SheetMetadata> findByTitles(List<String> titles);
}
