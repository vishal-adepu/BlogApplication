package com.blog.application.controllers;

import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.UserDTO;
import com.blog.application.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //POST - Create User
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){

        UserDTO createUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }

    //PUT - Update User
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto , @PathVariable("userId") Integer uid){

        UserDTO updatedUser = this.userService.updateUser(userDto, uid);

        return ResponseEntity.ok(updatedUser);
    }

    //GET - User get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
        UserDTO user = this.userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    //GET - get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return  ResponseEntity.ok(this.userService.getAllUsers());
    }


    //DELETE - Delete User
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){

        this.userService.deleteUser(userId);
        
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true ), HttpStatus.OK);
    }
}
