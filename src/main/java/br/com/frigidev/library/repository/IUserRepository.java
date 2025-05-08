package br.com.frigidev.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.frigidev.library.entity.User;

public interface IUserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUserEmail(String email);
}
