package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AbstractDAO <T> {
    Optional<T> get(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(T t) throws SQLException;
}
