package com.example.jpapersistence.common.repository;

public interface BaseRepository<D, T> {

    D findWithGraph(T id, String graphName);
}
