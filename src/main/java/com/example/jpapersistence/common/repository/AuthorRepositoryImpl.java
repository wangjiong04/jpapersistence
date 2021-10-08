package com.example.jpapersistence.common.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.jpapersistence.common.entity.NumberId.Author;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jdk.jfr.Registered;

@Registered
public class AuthorRepositoryImpl implements BaseRepository<Author, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author findWithGraph(Long id, String graphName) {
        EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(Author.class, id, properties);
    }
}
