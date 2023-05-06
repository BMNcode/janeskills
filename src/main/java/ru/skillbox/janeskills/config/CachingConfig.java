package ru.skillbox.janeskills.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CachingConfig extends CachingConfigurerSupport {

    public static final String SHEET_DATA = "SHEET_DATA";

    public static final String[] CACHES_LIST = {
            SHEET_DATA
    };

    @Override
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CACHES_LIST);
    }

    @CacheEvict(allEntries = true, value = SHEET_DATA)
    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 500)
    public void cacheEvictSheetData() {
    }
}
