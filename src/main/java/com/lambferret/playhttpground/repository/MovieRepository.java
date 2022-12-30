package com.lambferret.playhttpground.repository;

import com.lambferret.playhttpground.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    @Query("{'ownerSession': ?0}")
    List<Movie> findAllbyOwnerSession(String sessionId);
}
