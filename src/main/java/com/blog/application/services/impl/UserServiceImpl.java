package com.blog.application.services.impl;

import com.blog.application.entities.User;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.UserDTO;
import com.blog.application.repositories.UserRepository;
import com.blog.application.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);

    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepository.save(user);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        this.userRepository.delete(user);
    }



    //Using Model mapper to map User and UserDTO object mapping
    private User dtoToUser(UserDTO userDto){
       User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDTO userToDto(User user){
        UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
        return userDto;
    }

/*
    //Instead of this method we can use Model-Mapper Libraries to avoid manual mapping of models
    private User dtoToUser(UserDTO userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDTO userToDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }

 */
}
