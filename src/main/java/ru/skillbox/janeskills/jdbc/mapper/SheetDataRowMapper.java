package ru.skillbox.janeskills.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import org.springframework.jdbc.core.RowMapper;
import ru.skillbox.janeskills.domain.SheetData;

public class SheetDataRowMapper implements RowMapper<SheetData> {

    @Override
    public SheetData mapRow(ResultSet rs, int rowNum) throws SQLException {
        SheetData sheetData = new SheetData();
        sheetData.setMetadataId(rs.getLong("metadata_id"));
        sheetData.setRow(rs.getInt("row_num"));
        sheetData.setColumn(rs.getInt("column_num"));
        sheetData.setData(rs.getString("data"));
        sheetData.setUpdatedAt(rs.getObject("updated_at", OffsetDateTime.class));
        return sheetData;
    }
}
