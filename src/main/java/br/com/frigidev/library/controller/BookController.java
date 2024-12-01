package br.com.frigidev.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frigidev.library.entity.Book;
import br.com.frigidev.library.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	BookService bookService;
	
	@PostMapping
	public ResponseEntity<Book> createLoan(@RequestBody Book book) {
		Book newBook = bookService.saveBook(book);
		if(newBook != null)
			return new ResponseEntity<>(newBook, HttpStatus.CREATED);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		if(books != null) 
			return new ResponseEntity<>(books, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
		Book book = bookService.getBookById(id);
		if(book != null) 
			return new ResponseEntity<>(book, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteLoanById(@PathVariable Integer id) {
		Boolean deleted = bookService.deleteBook(id);
		if(deleted) 
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteAllBooks() {
		Boolean deleted = bookService.deleteAllBooks();
		if(deleted)
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Integer id) {
		Book bookUpdated = bookService.updateBook(book, id);
		if(bookUpdated != null) 
			return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
