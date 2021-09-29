package com.example.jpapersistence.idstrategy;

import static org.jeasy.random.FieldPredicates.named;

import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.randomizers.FullNameRandomizer;
import org.jeasy.random.randomizers.SentenceRandomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jpapersistence.common.SnowFlake;
import com.example.jpapersistence.common.entity.NumberId.Author;
import com.example.jpapersistence.common.entity.NumberId.Author_Algorithm;
import com.example.jpapersistence.common.entity.NumberId.Author_UUID_Update;
import com.example.jpapersistence.common.entity.UUID.Author_UUID;
import com.example.jpapersistence.common.measure.Measured;
import com.example.jpapersistence.common.repository.AuthorRepository;
import com.example.jpapersistence.common.repository.Author_Algorithm_Repository;
import com.example.jpapersistence.common.repository.Author_UUID_Repository;
import com.example.jpapersistence.common.repository.Author_UUID_Update_Repository;

@Component
public class IdCompare {
    @Autowired
    private AuthorRepository              authorRepository;

    @Autowired
    private Author_UUID_Repository        author_uuid_repository;

    @Autowired
    private Author_UUID_Update_Repository author_uuid_update_repository;

    @Autowired
    private Author_Algorithm_Repository   author_algorithm_repository;

    @Autowired
    private SnowFlake                     snowFlake;

    private final int                     max = 100000;

    @Measured(message = "Sequence id:")
    public void insertSequence() {
        for (int i = 0; i < max; i++) {
            Author author = new Author();
            authorRepository.save(author);
        }
    }

    @Measured(message = "UUID:")
    public void insertUUID() {
        for (int i = 0; i < max; i++) {
            Author_UUID author = new Author_UUID();
            author_uuid_repository.save(author);
        }
    }

    @Measured(message = "UUID to int64:")
    public void insertUUIDUpdate() {
        for (int i = 0; i < max; i++) {
            Author_UUID_Update author = new Author_UUID_Update();
            author_uuid_update_repository.save(author);
        }
    }

    @Measured(message = "Snow flake id:")
    public void insertSnowFlake() {
        for (int i = 0; i < max; i++) {
            Author_Algorithm author = new Author_Algorithm();
            author.setId(snowFlake.nextId());
            author_algorithm_repository.save(author);
        }
    }

    public void generateAssociate() {
        EasyRandomParameters parameters = new EasyRandomParameters().collectionSizeRange(4, 8)
            .stringLengthRange(1, 255).objectPoolSize(10000)
            .randomize(FieldPredicates.named("name"), new FullNameRandomizer())
            .randomize(FieldPredicates.named("age"), new IntegerRangeRandomizer(25, 60))
            .randomize(FieldPredicates.named("price"), new IntegerRangeRandomizer(15, 100))
            .randomize(FieldPredicates.named("title"), new SentenceRandomizer())
            .excludeField(named("author"))
            .randomize(FieldPredicates.named("grade"), new IntegerRangeRandomizer(1, 5))
            .randomize(FieldPredicates.named("title"), new SentenceRandomizer())
            .randomize(FieldPredicates.named("content"), new SentenceRandomizer())
            .excludeField(named("book")).excludeField(named("id"));
        EasyRandom easyRandom = new EasyRandom(parameters);
        List<Author> authors = easyRandom.objects(Author.class, 100).collect(Collectors.toList());
        authors.forEach(author -> {
            author.getBooks().forEach(book -> {
                book.setId(snowFlake.nextId());
                book.setAuthor(author);
                book.getComments().forEach(comment -> {
                    comment.setId(snowFlake.nextId());
                    comment.setBook(book);
                });
            });
        });
        authorRepository.saveAll(authors);

        List<Author_UUID> author_uuids = easyRandom.objects(Author_UUID.class, 100)
            .collect(Collectors.toList());
        author_uuids.forEach(author -> {
            author.getBooks().forEach(book -> {
                book.setAuthor(author);
                book.getComments().forEach(comment -> comment.setBook(book));
            });

        });
        author_uuid_repository.saveAll(author_uuids);
    }

}
