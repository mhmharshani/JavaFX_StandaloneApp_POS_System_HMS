package service.custom;

import dto.Doctor;
import dto.DoctorSession;
import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface DoctorService extends SuperService {

    boolean addDoctor(Doctor doctor) throws SQLException;

    boolean updateDoctor(Doctor doctor);

    List<String> searchDoctorByName(String name);

    Doctor searchDoctorByPhoneNo(String phoneNo);

    Doctor searchDoctorById(String id);

    boolean deleteDoctor(String id);

    List<Doctor> getAll();

    ObservableList getDoctorId();

    boolean addSession(DoctorSession doctorSession) throws SQLException;

    List<DoctorSession> getAllSessions();

    List<DoctorSession> searchDoctorSessionByDocId(String id);

    DoctorSession searchDoctorSession (String doctorId, String date, String time);

    String nextId();

    String nextSessionId();

    ObservableList getSessionNames();

    ObservableList getSessionStatus();
}
