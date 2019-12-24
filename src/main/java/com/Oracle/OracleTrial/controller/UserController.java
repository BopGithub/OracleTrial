package com.Oracle.OracleTrial.controller;

import com.Oracle.OracleTrial.Exception.ResourceNotFoundException;
import com.Oracle.OracleTrial.model.UserEntity;
import com.Oracle.OracleTrial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    /*Get All users*/
    @GetMapping("/user")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    /*Get a specific user*/
    @GetMapping ("/user/{id}")
    public Optional<UserEntity> getUser(@PathVariable(value = "id") Long id){
        return userRepository.findById(id);
    }

    /*Create new user and store to database*/
    @PostMapping("/adduser")
    public UserEntity addNewUser(@Valid @RequestBody UserEntity userEntity) {
            return userRepository.save(userEntity);
    }

    /*update a specific user*/
    @PutMapping("/user/{id}")
    public Map<String, Boolean> updateUser(@PathVariable(value = "id") Long userId, @RequestBody UserEntity userEntity) throws ResourceNotFoundException {

            UserEntity newUserEntity = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User Not Found"+userId));

            newUserEntity.setName(userEntity.getName());
            newUserEntity.setSalary(userEntity.getSalary());

        userRepository.save(newUserEntity);

        Map<String,Boolean> response = new HashMap<>();
        response.put("updated",Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/user/{id}")
    public Map<String,Boolean> deleteUser(@PathVariable(value = "id") Long userId, @RequestBody UserEntity userEntity) throws ResourceNotFoundException{

        UserEntity newUserEntity = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"+userId));

        userRepository.delete(newUserEntity);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}
