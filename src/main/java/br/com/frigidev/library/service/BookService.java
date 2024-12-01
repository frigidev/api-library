package br.com.frigidev.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frigidev.library.entity.Book;
import br.com.frigidev.library.repository.IBookRepository;

@Service
public class BookService {
	@Autowired
	IBookRepository bookRepository;
	
	//Create Book
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}
	
	//Get All Books
	public List<Book> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		if(books != null)
			return books;

		return null;
	}
	
	//Get Book By Id
	public Book getBookById(Integer id) {
		Book book = bookRepository.findById(id).orElse(null);
		if(book != null)
			return book;
		
		return null;
	}
	
	//Delete Book By Id
	public boolean deleteBook(Integer id) {
		boolean exists = bookRepository.findById(id).isPresent();
		if(exists) {
			bookRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	//Delete All Books
	public boolean deleteAllBooks() {
		List<Book> books = bookRepository.findAll();
		if(books != null) {
			bookRepository.deleteAll(books);
			return true;
		}
		
		return false;
	}
	
	//Update Book By Id
	public Book updateBook(Book book, Integer id) {
		Book bookUpdated = bookRepository.findById(id).orElse(null);
		if(book == bookUpdated) {
			return book;
		}else if(bookUpdated == null) {
			return null;
		}else{
			bookUpdated.setAuthor(book.getAuthor());
			bookUpdated.setBookLoan(book.getBookLoan());
			bookUpdated.setTitle(book.getTitle());
			bookRepository.save(bookUpdated);
			return bookUpdated;
		}
	}
	
}
