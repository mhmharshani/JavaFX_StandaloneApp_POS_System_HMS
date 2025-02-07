package controller.doctor;

import model.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorService {

    boolean addDoctor(Doctor doctor) throws SQLException;

    boolean updateDoctor(Doctor doctor);

    Doctor searchDoctorByName(String name);

    Doctor searchDoctorByPhoneNo(String phoneNo);

    Doctor searchDoctorById(String id);

    boolean deleteDoctor(String id);

    List<Doctor> getAll();


}