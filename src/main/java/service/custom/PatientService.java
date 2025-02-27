package service.custom;

import javafx.collections.ObservableList;
import dto.Patient;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface PatientService extends SuperService {

    boolean addPatient(Patient patient) throws SQLException;

    boolean updatePatient(Patient patient);

    Patient searchPatientByNIC(String nic);

    Patient searchPatientByPhoneNo(String phoneNo);

    Patient searchPatientById(String id);

    boolean deletePatient(String id);

    List<Patient> getAll();

    String nextId();

    ObservableList getPatientId();
}
