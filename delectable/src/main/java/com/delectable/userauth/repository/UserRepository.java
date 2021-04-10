package com.delectable.userauth.repository;

import java.util.Optional;
import com.delectable.userauth.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Page<User> findByUsernameNot(Pageable pageable, String name);

    Page<User> findByIdNot(Pageable pageable, int i);

    Page<User> findByIdNot(Pageable pageable, long l);
}