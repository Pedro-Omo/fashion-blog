package com.fashionblog.controller;

import com.fashionblog.dtos.requestDto.LoginDto;
import com.fashionblog.dtos.requestDto.UserDto;
import com.fashionblog.dtos.responseDto.AdminResponseDto;
import com.fashionblog.dtos.responseDto.UserResponseDto;
import com.fashionblog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserDto userDto) {
        UserResponseDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}/getUserById")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        UserResponseDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/updateUser")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        UserResponseDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}/deleteUser")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/login")
//    public ResponseEntity<Void> login(@RequestParam String email, @RequestParam String password) {
//        userService.login(email, password);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest servletRequest) {
        UserResponseDto userResponseDto = userService.login(loginDto, servletRequest);
        return ResponseEntity.ok(userResponseDto);
    }
}





