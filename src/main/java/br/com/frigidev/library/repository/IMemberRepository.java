package br.com.frigidev.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frigidev.library.entity.Member;

public interface IMemberRepository extends JpaRepository<Member, Integer> {

}
