package br.com.frigidev.library.entity;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="loan")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="loan_id")
	private Integer loanId;
	
	@Column(name="loan_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date loanDate;
	
	@Column(name="devolution_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date devolutionDate;
	
	@OneToMany(mappedBy="bookLoan")
	@JsonManagedReference
	private Set<Book> books;
	
	@ManyToOne
	@JoinColumn(name="member_id", referencedColumnName="member_id")
	@JsonBackReference
	private Member member;
	
	public Integer getLoanId() {
		return loanId;
	}
	
	public Date getLoanDate() {
		return loanDate;
	}
	
	public Date getDevolutionDate() {
		return devolutionDate;
	}
	
	public Set<Book> getBooks() {
		return books;
	}
	
	public Member getMember() {
		return member;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	
	public void setDevolutionDate(Date devolutionDate) {
		this.devolutionDate = devolutionDate;
	}
	
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
}
