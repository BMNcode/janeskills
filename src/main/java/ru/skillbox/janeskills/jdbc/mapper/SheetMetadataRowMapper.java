package ru.skillbox.janeskills.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ru.skillbox.janeskills.domain.SheetMetadata;

public class SheetMetadataRowMapper implements RowMapper<SheetMetadata> {

    @Override
    public SheetMetadata mapRow(ResultSet rs, int rowNum) throws SQLException {
        SheetMetadata metadata = new SheetMetadata();
        metadata.setId(rs.getLong("id"));
        metadata.setTitle(rs.getString("title"));
        metadata.setRowCount(rs.getInt("row_count"));
        return metadata;
    }
}
