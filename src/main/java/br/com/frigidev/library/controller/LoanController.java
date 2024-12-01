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

import br.com.frigidev.library.entity.Loan;
import br.com.frigidev.library.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

	@Autowired
	LoanService loanService;
	
	@PostMapping
	public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
		Loan newLoan = loanService.saveLoan(loan);
		if(newLoan != null)
			return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public ResponseEntity<List<Loan>> getAllLoans() {
		List<Loan> loans = loanService.getAllLoans();
		if(loans != null) 
			return new ResponseEntity<>(loans, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Loan> getLoanById(@PathVariable Integer id) {
		Loan loan = loanService.getLoanById(id);
		if(loan != null) 
			return new ResponseEntity<>(loan, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteLoanById(@PathVariable Integer id) {
		Boolean deleted = loanService.deleteLoan(id);
		if(deleted) 
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteAllLoans() {
		Boolean deleted = loanService.deleteAllLoans();
		if(deleted)
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan, @PathVariable Integer id) {
		Loan loanUpdated = loanService.updateLoan(loan, id);
		if(loanUpdated != null) 
			return new ResponseEntity<>(loanUpdated, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
