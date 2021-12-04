package com.uva.users.repository;

import com.uva.users.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>{

    @Query
    public User findUserByEmailAndPassword(String email, String password);
}
