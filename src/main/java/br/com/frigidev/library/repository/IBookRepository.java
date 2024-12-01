package br.com.frigidev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frigidev.library.entity.Book;

public interface IBookRepository extends JpaRepository<Book, Integer> {
	
}
