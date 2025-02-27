package repository.custom.impl;

import db.DBConnection;
import dto.Doctor;
import dto.DoctorSession;
import javafx.scene.control.Alert;
import repository.custom.DoctorDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public boolean save(Doctor entity) throws SQLException {
        String SQL = "INSERT INTO doctor VALUES (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setString(2, entity.getSpeciality());
            pstm.setString(3, entity.getAvailability());
            pstm.setString(4, entity.getEmpId());
            boolean isDoctorAdded = pstm.executeUpdate() > 0;
            if(isDoctorAdded){
                connection.commit();
                return true;
            }

        }finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public boolean update(String s, Doctor entity) {
        return false;
    }

    @Override
    public Doctor search(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM doctor WHERE doctor_id =" + "'" + id + "'");
            if(resultSet.next()) {
                return new Doctor(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
            else{
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            int res = connection.createStatement().executeUpdate("DELETE FROM doctor WHERE doctor_id=" + "'" + id + "'");
            if(res>0) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted Success !!").show();
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Doctor> getAll() {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        try {

            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM doctor");
            while(resultSet.next()){
                Doctor doctor = new Doctor(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                doctorArrayList.add(doctor);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return doctorArrayList;
    }

    @Override
    public List<String> searchDoctorByName(String name) {
        ArrayList<String> docIdList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM employee WHERE name =" + "'" + name + "'");

            ResultSet resultSet2;
            while(resultSet.next()) {
                resultSet2 = connection.createStatement().executeQuery("SELECT * FROM doctor WHERE employee_id =" + "'" + resultSet.getString(1) + "'");
                while(resultSet2.next()) {
                    docIdList.add(resultSet2.getString(1));
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return docIdList;
    }

    @Override
    public boolean saveSession(DoctorSession entity) throws SQLException {
        String SQL = "INSERT INTO doctor_session VALUES (?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setString(2, entity.getName());
            pstm.setDate(3, java.sql.Date.valueOf(entity.getDate()));
            pstm.setTime(4, java.sql.Time.valueOf(entity.getTime()));
            pstm.setString(5, entity.getNumberLimit());
            pstm.setString(6, entity.getStatus());
            pstm.setString(7, entity.getDoctorId());
            Boolean isSessionAdded = pstm.executeUpdate() > 0;
            if(isSessionAdded){
                connection.commit();
                return true;
            }

        } finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public List<DoctorSession> getAllSessions() {
        ArrayList<DoctorSession> doctorSessionArrayList = new ArrayList<>();
        try {

            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM doctor_session");
            while(resultSet.next()){
                DoctorSession doctorSession = new DoctorSession(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                doctorSessionArrayList.add(doctorSession);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return doctorSessionArrayList;
    }

    @Override
    public List<DoctorSession> searchDoctorSessionByDocId(String id) {
        ArrayList<DoctorSession> doctorSessionArrayList = new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM doctor_session WHERE doctor_id =" + "'" + id + "'");

            while(resultSet.next()) {

                doctorSessionArrayList.add(new DoctorSession(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                ));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctorSessionArrayList;
    }

    @Override
    public DoctorSession searchDoctorSession(String doctorId, String date, String time) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM doctor_session WHERE doctor_id =" + "'" + doctorId + "'" + "AND date =" + "'" + date + "'" + "AND time =" + "'" + time + "'");

            if(resultSet.next()) {

                return new DoctorSession(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
