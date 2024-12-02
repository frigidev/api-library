package br.com.frigidev.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frigidev.library.entity.Loan;
import br.com.frigidev.library.repository.ILoanRepository;

@Service
public class LoanService {
	@Autowired
	private ILoanRepository loanRepository;
	
	public Loan saveLoan(Loan loan) {
		return loanRepository.save(loan);
	}
	
	public List<Loan> getAllLoans() {
		List<Loan> loans = loanRepository.findAll();
		if(loans != null)
			return loans;
	
		return null;
	}
	
	public Loan getLoanById(Integer id) {
		Loan loan = loanRepository.findById(id).orElse(null);
		if(loan != null)
			return loan;
		
		return null;
	}
	
	public boolean deleteLoan(Integer id) {
		boolean exists = loanRepository.findById(id).isPresent();
		if(exists) {
			loanRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean deleteAllLoans() {
		List<Loan> loans = loanRepository.findAll();
		if(loans != null) {
			loanRepository.deleteAll(loans);
			return true;
		}
		
		return false;
	}
	
	public Loan updateLoan(Loan loan, Integer id) {
		Loan loanUpdated = loanRepository.findById(id).orElse(null);
		if(loan == loanUpdated) {
			return loan;
		}else if (loanUpdated == null) {
			return null;
		}else{
			loanUpdated.setDevolutionDate(loan.getDevolutionDate());
			loanUpdated.setBooks(loan.getBooks());
			loanUpdated.setLoanDate(loan.getLoanDate());
			loanUpdated.setMember(loan.getMember());
			return loanUpdated;
		}
	}
	
}
