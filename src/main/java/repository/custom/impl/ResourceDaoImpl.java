package repository.custom.impl;

import db.DBConnection;
import dto.Appointment;
import dto.MedicalRecord;
import dto.Patient;
import dto.Room;
import javafx.scene.control.Alert;
import repository.custom.ResourceDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {
    @Override
    public boolean save(Room entity) throws SQLException {
        delete(entity.getId());
        String SQL = "INSERT INTO room VALUES (?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setString(2, entity.getType());
            pstm.setString(3, entity.getCapacity());
            pstm.setString(4, entity.getAvailability());
            pstm.setString(5, entity.getPatientId());

            boolean isRoomAdded = pstm.executeUpdate() > 0;
            if(isRoomAdded){
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
    public boolean update(String id, Room entity) throws SQLException {
        String SQL = "UPDATE room SET availability="+"'"+entity.getAvailability()+"'"+"AND patient_id="+"'"+entity.getPatientId()+"'"+"WHERE patient_id=" + "'" + id + "'";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            int res = connection.createStatement().executeUpdate(SQL);
            if(res>0) {
                new Alert(Alert.AlertType.CONFIRMATION,"Updated Success !!").show();
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
    public Room search(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM room WHERE room_id=" + "'" + id + "'");
            if(resultSet.next()) {
                return new Room(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
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
            int res = connection.createStatement().executeUpdate("DELETE FROM room WHERE room_id=" + "'" + id + "'");
            if(res>0) {
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
    public List<Room> getAll() {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM room");

            while(resultSet.next()){
                Room room = new Room(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                roomArrayList.add(room);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return roomArrayList;
    }

    @Override
    public boolean addMedicalRecord(MedicalRecord entity) throws SQLException{
        String SQL = "INSERT INTO medical_record VALUES (?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setDate(2, java.sql.Date.valueOf(entity.getDate()));
            pstm.setString(3, entity.getDiagnosis());
            pstm.setString(4, entity.getTreatment());
            pstm.setString(5, entity.getPatientId());

            boolean isRoomAdded = pstm.executeUpdate() > 0;
            if(isRoomAdded){
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        connection.rollback();
        return false;
    }

    @Override
    public List<MedicalRecord> getAllRecords() {
        ArrayList<MedicalRecord> medicalRecordsArrayList = new ArrayList<>();
        try {

            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM medical_record");
            while(resultSet.next()){
                MedicalRecord medicalRecord = new MedicalRecord(
                        resultSet.getString(1),
                        resultSet.getDate(2).toString(),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                medicalRecordsArrayList.add(medicalRecord);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return medicalRecordsArrayList;
    }
}
