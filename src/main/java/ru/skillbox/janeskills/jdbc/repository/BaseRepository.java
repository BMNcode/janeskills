package ru.skillbox.janeskills.jdbc.repository;

import java.util.List;

/**
 * Base method for work with repository.
 */
public interface BaseRepository<E> {

    void save(E entity);

    void saveAll(List<E> entities);

    void update(E entity);

    void updateAll(List<E> entities);

    E findById(long id);

    List<E> findAll();

    void delete(long id);

}
