package ru.skillbox.janeskills.service.scheduler.impl;

import java.util.Collection;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.janeskills.jdbc.service.SheetDataService;
import ru.skillbox.janeskills.jdbc.service.SheetMetadataService;
import ru.skillbox.janeskills.service.integration.google.sheets.GoogleSheetsService;
import ru.skillbox.janeskills.service.scheduler.SchedulerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SheetsSchedulerServiceImpl implements SchedulerService {

    private final SheetDataService sheetDataService;
    private final GoogleSheetsService googleSheetsService;
    private final SheetMetadataService sheetMetadataService;


    @Override
    @Scheduled(fixedDelay = 1000000)
    public void updateSheetMetadata() {
        log.info("Start update sheets metadata");
        sheetMetadataService.createOrUpdate(googleSheetsService.getProperties());
        log.info("End update sheets metadata");
    }

    @Override
    @Scheduled(fixedDelay = 300000)
    public void updateSheetData() {
        log.info("Start update sheets data");
        Stream.ofNullable(sheetMetadataService.findAll())
                .flatMap(Collection::stream)
                .forEach(metadata ->
                        sheetDataService.handleData(
                                googleSheetsService.getRowsByTitleRange(
                                        metadata.getTitle(), metadata.getRowCount()),
                                metadata)
                );
        log.info("End update sheets data");
    }
}
