package com.fashionblog.services.Implementation;

import com.fashionblog.dtos.requestDto.AdminDto;
import com.fashionblog.dtos.requestDto.LoginDto;
import com.fashionblog.dtos.responseDto.AdminResponseDto;
import com.fashionblog.entities.Admin;
import com.fashionblog.exceptions.CustomAppException;
import com.fashionblog.exceptions.ResourceNotFoundException;
import com.fashionblog.repository.AdminRepository;
import com.fashionblog.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;
    @Autowired
    private HttpSession session;


    public AdminServiceImplementation(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminResponseDto createAdmin(AdminDto adminDto) {
        Optional<Admin> existingAdmin = adminRepository.findByEmail(adminDto.getEmail());
        if (existingAdmin.isPresent()) {
            throw new CustomAppException("Admin with the same email already exists");
        }

        Admin admin = new Admin();
        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());

        Admin savedAdmin = adminRepository.save(admin);

        return mapToAdminResponseDto(savedAdmin);
    }


    @Override
    public AdminResponseDto getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));

        return mapToAdminResponseDto(admin);
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();

        return admins.stream()
                .map(this::mapToAdminResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Long adminId) {
        if (!adminRepository.existsById(adminId)) {
            throw new ResourceNotFoundException("Admin not found");
        }

        adminRepository.deleteById(adminId);
    }

    @Override
    public AdminResponseDto updateAdmin(AdminDto adminDto, Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));


        admin.setName(adminDto.getName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getPassword());
        admin.setUsername(adminDto.getUsername());

        Admin updatedAdmin = adminRepository.save(admin);

        return mapToAdminResponseDto(updatedAdmin);
    }

    @Override
    public AdminResponseDto login(LoginDto loginDto, HttpServletRequest request) {
        Optional<Admin>optionalAdmin = adminRepository.findByEmail(loginDto.getEmail());
        if(optionalAdmin.isEmpty()){
            throw new ResourceNotFoundException("The guy nor dey");
        }
        Admin admin = optionalAdmin.get();
        if (!admin.getPassword().equals(loginDto.getPassword())) {
            throw new CustomAppException("Invalid email or password");
        }
        session = request.getSession();
        session.setAttribute("adminId", admin.getId());
        return  AdminResponseDto.builder().adminId(admin.getId()).email(admin.getEmail()).name(admin.getName()).build();


    }


    private AdminResponseDto mapToAdminResponseDto(Admin admin) {
        return AdminResponseDto.builder()
                .adminId(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .build();
    }

}
