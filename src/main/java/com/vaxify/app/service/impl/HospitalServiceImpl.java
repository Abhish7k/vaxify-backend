package com.vaxify.app.service.impl;

import com.vaxify.app.dtos.hospital.HospitalResponse;
import com.vaxify.app.dtos.hospital.StaffHospitalRegisterRequest;
import com.vaxify.app.entities.Hospital;
import com.vaxify.app.entities.User;
import com.vaxify.app.entities.enums.HospitalStatus;
import com.vaxify.app.entities.enums.Role;
import com.vaxify.app.repository.HospitalRepository;
import com.vaxify.app.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public HospitalResponse registerHospital(
            StaffHospitalRegisterRequest request,
            User staffUser
    ) {

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
    public HospitalResponse getMyHospital(User staffUser) {

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
