package org.example.app.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T,S> {
    boolean create(S request);
    Optional<List<T>> fetchAll();
    Optional<T> fetchById(Long id);
    boolean updateById(Long id, S request);
    boolean deleteById(Long id);
}
