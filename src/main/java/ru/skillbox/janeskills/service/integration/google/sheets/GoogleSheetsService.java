package ru.skillbox.janeskills.service.integration.google.sheets;

import com.google.api.services.sheets.v4.model.SheetProperties;
import java.util.List;

/**
 * Interface for working google sheets
 */
public interface GoogleSheetsService {

    /**
     * Get all row from google sheet with range
     *
     * @param title the title sheet.
     * @param range the range in which we take data from the table page.
     */
    List<List<String>> getRowsByTitleRange(String title, Integer range);

    /**
     * Get properties google sheet.
     */
    List<SheetProperties> getProperties();
}
