package ru.skillbox.janeskills.jdbc.service;

import java.util.List;
import ru.skillbox.janeskills.dto.SheetDataDto;

public interface BaseDataService<T> {

    void save(T entity);

    void saveAll(List<T> entities);

    void update(T entity);

    T findById();

    T findAll();

    T findAllById();

}
