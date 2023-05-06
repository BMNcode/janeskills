package ru.skillbox.janeskills.jdbc.repository.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.skillbox.janeskills.domain.SheetData;
import ru.skillbox.janeskills.jdbc.repository.BaseRepository;

@Repository
@RequiredArgsConstructor
public class SheetRepositoryImpl implements BaseRepository<SheetData> {

    @Override
    public void save(SheetData entity) {

    }

    @Override
    public void saveAll(List<SheetData> entities) {

    }

    @Override
    public void update(SheetData entity) {

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
    public void delete(long id) {

    }
}
