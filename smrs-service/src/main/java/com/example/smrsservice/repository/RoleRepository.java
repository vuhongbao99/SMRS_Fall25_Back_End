package com.example.smrsservice.repository;

import com.example.smrsservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
}
