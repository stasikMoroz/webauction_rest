package com.vironit.businessauction.dao;

import java.util.List;

/**
 * Created by user on 10.02.2019.
 */
public interface BaseDao<T> {
    T save(T t);
    T getById(Long id);
    void update(T t);
    void delete(Long id);
    List<T> getAll();
}
