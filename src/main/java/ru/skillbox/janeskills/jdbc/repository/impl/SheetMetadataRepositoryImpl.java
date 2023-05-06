package ru.skillbox.janeskills.jdbc.repository.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.skillbox.janeskills.domain.SheetMetadata;
import ru.skillbox.janeskills.jdbc.mapper.SheetMetadataRowMapper;
import ru.skillbox.janeskills.jdbc.repository.SheetMetadataRepository;

@Repository
@RequiredArgsConstructor
public class SheetMetadataRepositoryImpl implements SheetMetadataRepository {

    private static final String CREATE_QUERY = """ 
            INSERT INTO sheet_metadata (title, row_count)
            VALUES (?,?)
            """;

    private static final String UPDATE_QUERY = """ 
            UPDATE sheet_metadata set title = ?, row_count = ?
            WHERE id = ?
             """;

    private static final String FIND_ALL_QUERY = """
            SELECT * FROM sheet_metadata
            """;

    private static final String FIND_BY_TITLES_QUERY = """
            SELECT * FROM sheet_metadata
            WHERE title IN (%s)
            """;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(SheetMetadata entity) {
        jdbcTemplate.update(CREATE_QUERY, entity.getTitle(), entity.getRowCount());
    }

    @Override
    public void saveAll(List<SheetMetadata> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            for (SheetMetadata entity : entities) {
                jdbcTemplate.update(CREATE_QUERY, entity.getTitle(), entity.getRowCount());
            }
        }
    }

    @Override
    public void update(SheetMetadata entity) {

    }

    @Override
    public void updateAll(List<SheetMetadata> entities) {
        if (CollectionUtils.isNotEmpty(entities)) {
            for (SheetMetadata entity : entities) {
                jdbcTemplate.update(UPDATE_QUERY, entity.getTitle(), entity.getRowCount(), entity.getId());
            }
        }
    }

    @Override
    public SheetMetadata findById(long id) {
        return null;
    }

    @Override
    public List<SheetMetadata> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, new SheetMetadataRowMapper());
    }

    @Override
    public List<SheetMetadata> findByTitles(List<String> titles) {
        return jdbcTemplate.query(String.format(FIND_BY_TITLES_QUERY, Stream.ofNullable(titles)
                .flatMap(Collection::stream)
                .map(str -> "'" + str + "'")
                .collect(Collectors.joining(","))), new SheetMetadataRowMapper());
    }

    @Override
    public void delete(long id) {

    }
}
