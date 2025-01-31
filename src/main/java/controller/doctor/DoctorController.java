package controller.doctor;

import model.Doctor;

import java.util.List;

public class DoctorController implements DoctorService {

    @Override
    public boolean addDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public Doctor searchDoctorByName(String name) {
        return null;
    }

    @Override
    public Doctor searchDoctorByPhoneNo(String phoneNo) {
        return null;
    }

    @Override
    public Doctor searchDoctorById(String Id) {
        return null;
    }

    @Override
    public boolean deleteDoctor(String id) {
        return false;
    }

    @Override
    public List<Doctor> getAll() {
        return List.of();
    }
}
