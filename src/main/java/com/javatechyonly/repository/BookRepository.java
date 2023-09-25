package com.javatechyonly.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.javatechyonly.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
