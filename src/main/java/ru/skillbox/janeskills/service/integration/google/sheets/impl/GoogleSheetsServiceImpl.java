package ru.skillbox.janeskills.service.integration.google.sheets.impl;

import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skillbox.janeskills.config.GoogleProperties;
import ru.skillbox.janeskills.config.GoogleSheetsConfig;
import ru.skillbox.janeskills.exception.BadRequestException;
import ru.skillbox.janeskills.service.integration.google.sheets.GoogleSheetsService;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final GoogleProperties gProperties;
    private final GoogleSheetsConfig googleSheetsConfig;

    @Override
    public List<List<String>> getRowsByTitleRange(String title, Integer range) {
        try {
            return googleSheetsConfig.getSheets()
                    .spreadsheets()
                    .values()
                    .get(gProperties.getSpreadSheetsId(), String.format("%s!1:%d", title, range))
                    .execute()
                    .getValues().stream()
                    .map(sheet -> sheet.stream()
                            .map(String::valueOf)
                            .toList())
                    .toList();
        } catch (Exception e) {
            throw new BadRequestException("Error get row from google sheet with message: ", e.getMessage());
        }
    }

    @Override
    public List<SheetProperties> getProperties() {
        try {
            return googleSheetsConfig.getSheets()
                    .spreadsheets()
                    .get(gProperties.getSpreadSheetsId())
                    .setIncludeGridData(false)
                    .execute()
                    .getSheets()
                    .stream()
                    .map(Sheet::getProperties)
                    .toList();
        } catch (Exception e) {
            throw new BadRequestException("Error get properties from google sheet with message: ", e.getMessage());
        }
    }
}
