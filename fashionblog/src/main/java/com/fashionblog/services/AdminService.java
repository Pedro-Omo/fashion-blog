package com.fashionblog.services;

import com.fashionblog.dtos.requestDto.AdminDto;
import com.fashionblog.dtos.requestDto.LoginDto;
import com.fashionblog.dtos.responseDto.AdminResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AdminService {
    AdminResponseDto createAdmin(AdminDto adminDto);

    AdminResponseDto getAdminById(Long adminId);

    List<AdminResponseDto> getAllAdmins();

    void deleteAdmin(Long adminId);

    AdminResponseDto updateAdmin(AdminDto adminDto, Long adminId);

    AdminResponseDto login(LoginDto loginDto, HttpServletRequest request);
}





//package com.fashionblog.services;
//
//public interface AdminService {
//}
