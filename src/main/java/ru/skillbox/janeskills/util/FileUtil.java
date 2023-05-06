package ru.skillbox.janeskills.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtil {

    /**
     * Read file from path.
     *
     * @param path file path.
     * @return file inputStream.
     */
    @SneakyThrows
    public static InputStream readFile(String path) {
        var file = new File(path);
        return new FileInputStream(file);
    }
}
