package com.ex.dao;

import java.util.List;

public interface Persistable<T, ID> {
    T getById(int id);
    List<T> getAll();
    ID save(T obj);
    void delete(T obj);
    void update(T obj);
}
