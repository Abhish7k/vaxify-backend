package com.vaxify.app.controller;

import com.vaxify.app.dtos.hospital.HospitalResponse;
import com.vaxify.app.dtos.hospital.StaffHospitalRegisterRequest;
import com.vaxify.app.service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/register")
    public HospitalResponse registerHospital(
            @Valid @RequestBody StaffHospitalRegisterRequest request,
            @AuthenticationPrincipal UserDetails principal
    ) {
        return hospitalService.registerHospital(
                request,
                principal.getUsername() // email
        );
    }


    @GetMapping("/my")
    public HospitalResponse getMyHospital() {

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        return hospitalService.getMyHospital(email);
    }

}
