package com.example.jpapersistence.common.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.jpapersistence.common.entity.UUID.Author_UUID;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jdk.jfr.Registered;

@Registered
public class Author_UUID_RepositoryImpl implements BaseRepository<Author_UUID, String> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author_UUID findWithGraph(String id, String graphName) {
        EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", entityGraph);
        return entityManager.find(Author_UUID.class, id, properties);
    }
}
