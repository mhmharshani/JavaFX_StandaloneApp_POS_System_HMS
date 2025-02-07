package controller.doctor;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Doctor;
import model.DoctorSession;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController implements DoctorService {

    public String nextId(){
        List<Doctor> all = getAll();
        ArrayList<String> doctorIds = new ArrayList<>();
        all.forEach(doctor ->{
            doctorIds.add((doctor.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!doctorIds.isEmpty()){

            for(int i=0;i< doctorIds.size();i++){
                id=Integer.parseInt(doctorIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLD#0001";
        }
        return String.format("HLD#%04d",max);
    }

    @Override
    public boolean addDoctor(Doctor doctor) throws SQLException {
        String SQL = "INSERT INTO doctor VALUES (?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, doctor.getId());
            pstm.setString(2, doctor.getSpeciality());
            pstm.setString(3, doctor.getAvailability());
            pstm.setString(4, doctor.getEmpId());
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
    public Doctor searchDoctorById(String id) {
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
    public boolean deleteDoctor(String id) {
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

    public ObservableList getDoctorId(){

        ObservableList<String> doctorIds = FXCollections.observableArrayList();
        List<Doctor> all = getAll();

        all.forEach(doctor -> {
            doctorIds.add(doctor.getId());
        });
        return doctorIds;
    }

    public ObservableList getSpeciality(){

        ObservableList<String> specialityTypes = FXCollections.observableArrayList();
        List<Doctor> all = getAll();

        all.forEach(doctor -> {
            specialityTypes.add(doctor.getSpeciality());
        });
        return specialityTypes;
    }

    //-----------------Doctor Session Controller--------------------------------------

    public boolean addSession(DoctorSession doctorSession) throws SQLException {
        String SQL = "INSERT INTO doctor_session VALUES (?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, doctorSession.getId());
            pstm.setString(2, doctorSession.getName());
            pstm.setDate(3, java.sql.Date.valueOf(doctorSession.getDate()));
            pstm.setTime(4, java.sql.Time.valueOf(doctorSession.getTime()));
            pstm.setString(5, doctorSession.getNumberLimit());
            pstm.setString(6, doctorSession.getStatus());
            pstm.setString(7, doctorSession.getDoctorId());
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

    public String nextSessionId(){
        List<DoctorSession> all = getAllSessions();
        ArrayList<String> sessionIds = new ArrayList<>();
        all.forEach( doctorSession ->{
            sessionIds.add((doctorSession.getId().split("#"))[1]);
        });

        int id;
        int max = 0;

        if(!sessionIds.isEmpty()){
            for(int i=0;i< sessionIds.size();i++){
                id=Integer.parseInt(sessionIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLS#000001";
        }
        return String.format("HLS#%06d",max);
    }

    public ObservableList getSessionNames(){

        ObservableList<String> sessionNames = FXCollections.observableArrayList();
        sessionNames.add("Late Night Session(1-4AM)");
        sessionNames.add("Early Morning Session(4-7AM)");
        sessionNames.add("Morning Session(7-10AM)");
        sessionNames.add("Mid-day Session(10AM-1PM)");
        sessionNames.add("Evening Session(1-7PM)");
        sessionNames.add("Night Session(7-12PM)");

        return sessionNames;

    }

    public ObservableList getSessionStatus(){

        ObservableList<String> sessionStatus = FXCollections.observableArrayList();
        sessionStatus.add("Active");
        sessionStatus.add("Cancel");
        sessionStatus.add("Pending");

        return sessionStatus;

    }

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

    public DoctorSession searchDoctorSession (String doctorId, String date, String time) {

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
