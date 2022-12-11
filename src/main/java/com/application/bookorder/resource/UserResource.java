package com.application.bookorder.resource;

import com.application.bookorder.dto.UserDTO;
import com.application.bookorder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public UserDTO getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserDTO addNewUser(@RequestBody @Validated UserDTO userDTO){
        return userService.addNewUser(userDTO);
    }

    @PutMapping
    public UserDTO updateUserInfo(@RequestBody UserDTO userDTO){
        return userService.updateUserInfo(userDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}
