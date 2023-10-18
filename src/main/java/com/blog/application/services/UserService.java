package com.blog.application.services;

import com.blog.application.entities.User;
import com.blog.application.payloads.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {


   UserDTO createUser(UserDTO userDto);
   UserDTO updateUser(UserDTO userDto, Integer userId);
   UserDTO getUserById(Integer userId);
   List<UserDTO> getAllUsers();
   void deleteUser(Integer userId);

}
