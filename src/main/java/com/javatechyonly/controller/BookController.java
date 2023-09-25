package com.javatechyonly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechyonly.model.Book;
import com.javatechyonly.repository.BookRepository;


@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
//	private bookRepository bookRepository;
	private BookRepository bookRepository;
	
	@PostMapping("/addBook")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		bookRepository.save(book);
		return ResponseEntity.status(HttpStatus.OK).body("Book has added successfuly with id : "+book.getId());
	}
	
	@GetMapping("/getAllBook")
	public ResponseEntity<?> getAllBook() {
		return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
	}
	
	@GetMapping("/getBookById/{id}")
	public ResponseEntity<?> getBookById(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findById(id));
	}
	
	@DeleteMapping("/deleteBookById/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable int id) {
		bookRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Book has deleted successfuly with id : "+id);
	}
	
	
}
