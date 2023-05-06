package ru.skillbox.janeskills.config;

import static ru.skillbox.janeskills.util.Constant.GOOGLE_KEY;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GoogleSheetsConfig {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private final GoogleProperties gProperties;

    public Sheets getSheets() {
        NetHttpTransport transport = new NetHttpTransport.Builder().build();
        HttpRequestInitializer httpRequestInitializer = request -> request.setInterceptor(
                intercepted -> intercepted.getUrl().set(GOOGLE_KEY, gProperties.getApiKey())
        );

        return new Sheets.Builder(transport, JSON_FACTORY, httpRequestInitializer)
                .setApplicationName(gProperties.getAppName())
                .build();
    }
}
