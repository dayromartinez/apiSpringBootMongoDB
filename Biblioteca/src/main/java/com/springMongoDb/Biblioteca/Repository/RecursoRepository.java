package com.springMongoDb.Biblioteca.Repository;

import com.springMongoDb.Biblioteca.Collection.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RecursoRepository extends MongoRepository<Recurso, String> {
    Recurso findByName(String name);
}
