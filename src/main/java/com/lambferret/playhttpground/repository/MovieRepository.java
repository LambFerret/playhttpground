package com.lambferret.playhttpground.repository;

import com.lambferret.playhttpground.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    @Query("{ $or: [{'ownerSession': ?0},{'isFixed':true}] }")
    List<Movie> findAllAndOwnerSession(String sessionId);

    @Query(value = "{'ownerSession': ?0}", delete = true)
    void deleteAllbyOwnerSession(String sessionId);

    @Query(value = "{'isFixed': false}", delete = true)
    void deleteAllSession();

    @Query(value = "{'ownerSession': ?0, 'title': ?1}", delete = true)
    void deletebyOwnerSessionAndTitle(String sessionId, String title);

    @Query("{'ownerSession': ?0, 'title':?1}")
    Movie findAllbyOwnerSessionAndTitle(String sessionId, String title);
}
