package controller.doctor;

import model.Doctor;

import java.util.List;

public interface DoctorService {

    boolean addDoctor(Doctor doctor);

    boolean updateDoctor(Doctor doctor);

    Doctor searchDoctorByName(String name);

    Doctor searchDoctorByPhoneNo(String phoneNo);

    Doctor searchDoctorById(String id);

    boolean deleteDoctor(String id);

    List<Doctor> getAll();


}