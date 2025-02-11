package com.bush.myapplication.database.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<E> {
    public List<E> findAll(QueryArgument arguments);
    public Optional<E> findById(QueryArgument arguments);
}
