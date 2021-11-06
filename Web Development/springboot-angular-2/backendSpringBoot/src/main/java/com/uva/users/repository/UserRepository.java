package com.uva.users.repository;

import java.util.List;

import com.uva.users.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>{

    @Query
    public List<User> findByEnabledTrue();

    @Query
    public List<User> findByEnabledFalse();

}
