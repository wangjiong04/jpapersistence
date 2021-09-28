package com.example.jpapersistence.idstrategy;

import static org.jeasy.random.FieldPredicates.inClass;
import static org.jeasy.random.FieldPredicates.named;

import java.util.List;
import java.util.stream.Collectors;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.api.Randomizer;
import org.jeasy.random.randomizers.FullNameRandomizer;
import org.jeasy.random.randomizers.SentenceRandomizer;
import org.jeasy.random.randomizers.WordRandomizer;
import org.jeasy.random.randomizers.collection.ListRandomizer;
import org.jeasy.random.randomizers.range.IntegerRangeRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jpapersistence.common.SnowFlake;
import com.example.jpapersistence.common.entity.NumberId.*;
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
            //authorRepository.save(author);
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

    private Randomizer<Comment> getCommentRandomizer() {
        return new Randomizer<Comment>() {
            EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(
                    FieldPredicates.named("grade").and(FieldPredicates.inClass(Comment.class)),
                    new IntegerRangeRandomizer(1, 5))
                .randomize(FieldPredicates.named("title"), new WordRandomizer())
                .randomize(FieldPredicates.named("content"), new SentenceRandomizer())
                .excludeField(named("book").and(inClass(Comment.class)))
                .excludeField(named("id").and(inClass(Comment.class)));

            private EasyRandom   easyRandom = new EasyRandom(parameters);

            @Override
            public Comment getRandomValue() {
                return easyRandom.nextObject(Comment.class);
            }

        };

    }

    private Randomizer<Book> getBookRandomizer() {
        return new Randomizer<Book>() {
            EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(FieldPredicates.named("price").and(FieldPredicates.inClass(Book.class)),
                    new IntegerRangeRandomizer(15, 100))
                .randomize(FieldPredicates.named("title").and(FieldPredicates.inClass(Book.class)),
                    new WordRandomizer())
                .randomize(
                    FieldPredicates.named("comments").and(FieldPredicates.ofType(List.class))
                        .and(FieldPredicates.inClass(Book.class)),
                    new ListRandomizer<Comment>(getCommentRandomizer(), 10))
                .excludeField(named("author").and(inClass(Book.class)))
                .excludeField(named("id").and(inClass(Comment.class)));

            private EasyRandom   easyRandom = new EasyRandom(parameters);

            @Override
            public Book getRandomValue() {
                return easyRandom.nextObject(Book.class);
            }

        };
    }

    public void generateAssociate() {
        EasyRandomParameters parameters = new EasyRandomParameters().collectionSizeRange(2, 8)
            .randomize(FieldPredicates.named("name").and(FieldPredicates.inClass(Author.class)),
                new FullNameRandomizer())
            .randomize(FieldPredicates.named("age").and(FieldPredicates.inClass(Author.class)),
                new IntegerRangeRandomizer(25, 50))
            .randomize(
                FieldPredicates.named("books").and(FieldPredicates.ofType(List.class))
                    .and(FieldPredicates.inClass(Author.class)),
                new ListRandomizer<Book>(getBookRandomizer(), 5))
            .excludeField(named("id").and(inClass(Author.class)));
        EasyRandom easyRandom = new EasyRandom(parameters);
        List<Author> authors = easyRandom.objects(Author.class, 100).collect(Collectors.toList());
        authors.forEach(author -> {
            author.getBooks().forEach(book -> {
                book.setAuthor(author);
                book.getComments().forEach(comment -> comment.setBook(book));
            });
        });
        authorRepository.saveAll(authors);
    }

}
