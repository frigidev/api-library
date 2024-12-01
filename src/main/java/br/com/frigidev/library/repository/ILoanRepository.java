package br.com.frigidev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frigidev.library.entity.Loan;

public interface ILoanRepository extends JpaRepository<Loan, Integer> {

}
