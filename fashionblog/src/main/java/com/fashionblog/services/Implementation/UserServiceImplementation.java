package com.fashionblog.services.Implementation;

import com.fashionblog.dtos.requestDto.LoginDto;
import com.fashionblog.dtos.requestDto.UserDto;
import com.fashionblog.dtos.responseDto.AdminResponseDto;
import com.fashionblog.dtos.responseDto.UserResponseDto;
import com.fashionblog.entities.Admin;
import com.fashionblog.entities.UserTable;
import com.fashionblog.exceptions.CustomAppException;
import com.fashionblog.exceptions.ResourceNotFoundException;
import com.fashionblog.repository.UserRepository;
import com.fashionblog.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    @Autowired
    private HttpSession session;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto createUser(UserDto userDto) {
        // Check if userTable with the same email already exists
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new CustomAppException("UserTable with the same email already exists");
        }

        UserTable userTable = new UserTable();
        userTable.setName(userDto.getName());
        userTable.setEmail(userDto.getEmail());
        userTable.setPassword(userDto.getPassword());
        userTable.setUsername(userDto.getUsername());

        UserTable savedUserTable = userRepository.save(userTable);

        return mapToUserResponseDto(savedUserTable);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        UserTable userTable = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserTable not found"));

        return mapToUserResponseDto(userTable);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("UserTable not found");
        }

        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto updateUser(UserDto userDto, Long userId) {
        UserTable userTable = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserTable not found"));

        // Check if userTable with the updated email already exists
        Optional<UserTable> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(userId)) {
            throw new CustomAppException("UserTable with the same email already exists");
        }

        userTable.setName(userDto.getName());
        userTable.setEmail(userDto.getEmail());
        userTable.setPassword(userDto.getPassword());
        userTable.setUsername(userDto.getUsername());


        UserTable updatedUserTable = userRepository.save(userTable);

        return mapToUserResponseDto(updatedUserTable);
    }

//    @Override
//    public void login(String email, String password) {
//        // Retrieve userTable by email
//        UserTable userTable = userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("UserTable not found"));
//
//        // Check if the password matches
//        if (!userTable.getPassword().equals(password)) {
//            throw new CustomAppException("Invalid email or password");
//        }
//
//        // Perform any additional login logic, such as setting authentication tokens, etc.
//    }

    @Override
    public UserResponseDto login(LoginDto loginDto, HttpServletRequest request) {
        Optional<UserTable>optionalUserTable = userRepository.findByEmail(loginDto.getEmail());
        if(optionalUserTable.isEmpty()){
            throw new ResourceNotFoundException("The guy nor dey");
        }
        UserTable userTable = optionalUserTable.get();
        if (!userTable.getPassword().equals(loginDto.getPassword())) {
            throw new CustomAppException("Invalid email or password");
        }
        session = request.getSession();
        session.setAttribute("UserId", userTable.getId());
        return  UserResponseDto.builder().userId(userTable.getId()).email(userTable.getEmail()).name(userTable.getName()).build();


    }

    private UserResponseDto mapToUserResponseDto(UserTable userTable) {
        return UserResponseDto.builder()
                .userId(userTable.getId())
                .name(userTable.getName())
                .email(userTable.getEmail())
                .build();
    }
}
