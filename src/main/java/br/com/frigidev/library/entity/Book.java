package br.com.frigidev.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Book {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="book_id")
		private Integer bookId;
		
		@Column(name="title")
		private String title;
		
		@Column(name="author")
		private String author;
		
		@ManyToOne
		@JoinColumn(name="loan_id", referencedColumnName="loan_id")
		@JsonBackReference
		private Loan bookLoan;
		
		public Integer getBookId() {
			return bookId;
		}
		
		public String getTitle() {
			return title;
		}
	
		public String getAuthor() {
			return author;
		}
		
		public Loan getBookLoan() {
			return bookLoan;
		}
		
		public void setBookId(Integer bookId) {
			this.bookId = bookId;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public void setBookLoan(Loan bookLoan) {
			this.bookLoan = bookLoan;
		}
}
