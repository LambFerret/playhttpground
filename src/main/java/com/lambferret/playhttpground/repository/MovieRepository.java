package com.lambferret.playhttpground.repository;

import com.lambferret.playhttpground.document.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, ObjectId> {

}
