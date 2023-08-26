package com.fashionblog.services;

import com.fashionblog.dtos.requestDto.LoginDto;
import com.fashionblog.dtos.requestDto.UserDto;
import com.fashionblog.dtos.responseDto.AdminResponseDto;
import com.fashionblog.dtos.responseDto.UserResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    UserResponseDto createUser(UserDto userDto);

    UserResponseDto getUserById(Long userId);

    void deleteUser(Long userId);

    UserResponseDto updateUser(UserDto userDto, Long userId);

//    void login(String email, String password);

    UserResponseDto login(LoginDto loginDto, HttpServletRequest request);
}





//package com.fashionblog.services;
//
//public interface UserService {
//}
