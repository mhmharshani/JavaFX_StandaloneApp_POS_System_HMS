package repository.custom;

import dto.Doctor;
import dto.DoctorSession;
import repository.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface DoctorDao extends CrudDao<Doctor,String> {

    List<String> searchDoctorByName(String name);

    boolean saveSession(DoctorSession doctorSession) throws SQLException;

    List<DoctorSession> getAllSessions();

    List<DoctorSession> searchDoctorSessionByDocId(String id);

    DoctorSession searchDoctorSession (String doctorId, String date, String time);



}
