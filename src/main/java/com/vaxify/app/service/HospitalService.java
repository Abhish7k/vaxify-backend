package com.vaxify.app.service;

import com.vaxify.app.dtos.hospital.HospitalResponse;
import com.vaxify.app.dtos.hospital.StaffHospitalRegisterRequest;
import com.vaxify.app.entities.User;

public interface HospitalService {

    HospitalResponse registerHospital(
            StaffHospitalRegisterRequest request,
            User staffUser
    );

    HospitalResponse getMyHospital(User staffUser);
}
