package controller.patient;

import model.Patient;

import java.util.List;

public interface PatientService {

    boolean addPatient(Patient patient);

    boolean updatePatient(Patient patient);

    Patient searchPatientByNIC(String nic);

    Patient searchPatientByPhoneNo(String phoneNo);

    Patient searchPatientById(String id);

    boolean deletePatient(String id);

    List<Patient> getAll();


}
