package com.delectable.userauth.repository;

import java.util.Optional;
import com.delectable.userauth.models.ERole;
import com.delectable.userauth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}