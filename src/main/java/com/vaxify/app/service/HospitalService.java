package com.vaxify.app.service;

import com.vaxify.app.dtos.hospital.HospitalResponse;
import com.vaxify.app.dtos.hospital.StaffHospitalRegisterRequest;

public interface HospitalService {

    HospitalResponse registerHospital(
            StaffHospitalRegisterRequest request,
            String staffEmail
    );

    HospitalResponse getMyHospital(String staffEmail);
}
