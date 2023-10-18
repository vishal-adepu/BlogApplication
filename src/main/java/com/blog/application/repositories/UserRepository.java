package com.blog.application.repositories;

import com.blog.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface UserRepository extends JpaRepository<User, Integer> {

}
