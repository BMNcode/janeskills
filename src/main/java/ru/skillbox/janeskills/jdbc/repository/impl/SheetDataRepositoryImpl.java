package ru.skillbox.janeskills.jdbc.repository.impl;

import static ru.skillbox.janeskills.config.CachingConfig.SHEET_DATA;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.janeskills.domain.SheetData;
import ru.skillbox.janeskills.jdbc.mapper.SheetDataRowMapper;
import ru.skillbox.janeskills.jdbc.repository.SheetDataRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SheetDataRepositoryImpl implements SheetDataRepository {

    private static final String CREATE_QUERY = """ 
            INSERT INTO sheet_data (metadata_id, row_num, column_num, data, updated_at)
            VALUES (?,?,?,?,?)
            """;

    private static final String UPDATE_QUERY = """
            UPDATE sheet_data
            SET metadata_id = ?, row_num = ?, column_num = ?, data = ?, updated_at = ?
            WHERE id = ?
            """;

    private static final String FIND_ALL_BY_METADATA_ID = """
            SELECT * FROM sheet_data
            WHERE metadata_id = %d
            """;

    private static final String FIND_BY_METADATA_ID_ROW_COLUMN = """
            SELECT * FROM sheet_data
            WHERE metadata_id = ? AND row_num = ? AND column_num = ?
            """;

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void save(SheetData entity) {
        jdbcTemplate.update(CREATE_QUERY, entity.getMetadataId(), entity.getRow(), entity.getColumn(),
                entity.getData(), entity.getUpdatedAt());
    }

    @Override
    @Transactional
    public void saveAll(List<SheetData> entities) {

    }

    @Override
    @Transactional
    public void update(SheetData entity) {
        jdbcTemplate.update(UPDATE_QUERY, entity.getMetadataId(), entity.getRow(), entity.getColumn(),
                entity.getData(), entity.getUpdatedAt(), entity.getId());
    }

    @Override
    public void updateAll(List<SheetData> entities) {

    }

    @Override
    public SheetData findById(long id) {
        return null;
    }

    @Override
    public List<SheetData> findAll() {
        return null;
    }

    @Override
    public List<SheetData> findAllByMetadataId(Long id) {
        var query = String.format(FIND_ALL_BY_METADATA_ID, id);
        return jdbcTemplate.query(query, new SheetDataRowMapper());
    }

    @Override
    @CachePut(value = SHEET_DATA)
    public Optional<SheetData> findByMetadataIdAndRowAndColumnCache(Long id, Integer row, Integer column) {
        return Optional.of(jdbcTemplate.query(FIND_BY_METADATA_ID_ROW_COLUMN, new SheetDataRowMapper(), id, row, column))
                .filter(data -> !data.isEmpty())
                .map(data -> data.get(0));
    }

    @Override
    public void delete(long id) {

    }
}
