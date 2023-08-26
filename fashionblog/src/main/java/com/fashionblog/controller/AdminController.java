package com.fashionblog.controller;

import com.fashionblog.dtos.requestDto.AdminDto;
import com.fashionblog.dtos.requestDto.LoginDto;
import com.fashionblog.dtos.responseDto.AdminResponseDto;
import com.fashionblog.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<AdminResponseDto> createAdmin(@RequestBody AdminDto adminDto) {
        AdminResponseDto createdAdmin = adminService.createAdmin(adminDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @GetMapping("/{adminId}/getAdminById")
    public ResponseEntity<AdminResponseDto> getAdminById(@PathVariable Long adminId) {
        AdminResponseDto admin = adminService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/getAllAdmins")
    public ResponseEntity<List<AdminResponseDto>> getAllAdmins() {
        List<AdminResponseDto> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{adminId}/updateAdmin")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable Long adminId, @RequestBody AdminDto adminDto) {
        AdminResponseDto updatedAdmin = adminService.updateAdmin(adminDto, adminId);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{adminId}/deleteAdmin")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletRequest servletRequest) {
       AdminResponseDto adminResponseDto = adminService.login(loginDto, servletRequest);
        return ResponseEntity.ok(adminResponseDto);
    }
}




