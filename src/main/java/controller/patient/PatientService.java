package controller.patient;

import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientService {

    boolean addPatient(Patient patient) throws SQLException;

    boolean updatePatient(Patient patient);

    Patient searchPatientByNIC(String nic);

    Patient searchPatientByPhoneNo(String phoneNo);

    Patient searchPatientById(String id);

    boolean deletePatient(String id);

    List<Patient> getAll();


}
