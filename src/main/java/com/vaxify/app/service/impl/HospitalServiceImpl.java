package com.vaxify.app.service.impl;

import com.vaxify.app.dtos.hospital.HospitalResponse;
import com.vaxify.app.dtos.hospital.StaffHospitalRegisterRequest;
import com.vaxify.app.entities.Hospital;
import com.vaxify.app.entities.User;
import com.vaxify.app.entities.enums.HospitalStatus;
import com.vaxify.app.entities.enums.Role;
import com.vaxify.app.repository.HospitalRepository;
import com.vaxify.app.repository.UserRepository;
import com.vaxify.app.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    @Override
    public HospitalResponse registerHospital(
            StaffHospitalRegisterRequest request,
            String staffEmail
    ) {

        User staffUser = userRepository.findByEmail(staffEmail)
                .orElseThrow(() ->
                        new IllegalStateException("Staff user not found")
                );

        if (staffUser.getRole() != Role.STAFF) {
            throw new AccessDeniedException("Only hospital staff can register hospitals");
        }

        hospitalRepository.findByStaffUser(staffUser)
                .ifPresent(h -> {
                    throw new IllegalStateException(
                            "Hospital already registered for this staff"
                    );
                });

        Hospital hospital = Hospital.builder()
                .name(request.getName())
                .address(request.getAddress())
                .staffUser(staffUser)
                .status(HospitalStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        Hospital saved = hospitalRepository.save(hospital);

        return HospitalResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .address(saved.getAddress())
                .status(saved.getStatus())
                .build();
    }

    @Override
    public HospitalResponse getMyHospital(String staffEmail) {

        User staffUser = userRepository.findByEmail(staffEmail)
                .orElseThrow(() ->
                        new IllegalStateException("Staff user not found")
                );

        Hospital hospital = hospitalRepository.findByStaffUser(staffUser)
                .orElseThrow(() ->
                        new IllegalStateException("No hospital found for this staff")
                );

        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .status(hospital.getStatus())
                .build();
    }
}
