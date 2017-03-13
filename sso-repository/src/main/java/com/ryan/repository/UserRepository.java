package com.ryan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsernameAndEmail(String username, String email);

    Page<User> findByUsername(String username, Pageable pageRequest);

    Page<User> findByUsernameContaining(String username, Pageable pageRequest);
    
    User findByUsername(String username);
}
