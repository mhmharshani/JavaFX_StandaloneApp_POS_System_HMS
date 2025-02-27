package repository.custom.impl;

import db.DBConnection;
import dto.Appointment;
import javafx.scene.control.Alert;
import repository.custom.AppointmentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao {

    @Override
    public boolean save(Appointment entity) throws SQLException {
        String SQL = "INSERT INTO appointment VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setDate(2, java.sql.Date.valueOf(entity.getDate()));
            pstm.setTime(3, java.sql.Time.valueOf(entity.getTime()));
            pstm.setInt(4, entity.getNumber());
            pstm.setString(5, entity.getStatus());
            pstm.setString(6, entity.getDoctorId());
            pstm.setString(7, entity.getPatientId());
            pstm.setString(8,entity.getSessionId());
            Boolean isAppointmentAdded = pstm.executeUpdate() > 0;
            if(isAppointmentAdded){
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
    public boolean update(String s, Appointment entity) {
        return false;
    }

    @Override
    public Appointment search(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM appointment WHERE appointment_id =" + "'" + id + "'");
            if(resultSet.next()) {
                return new Appointment(
                        resultSet.getString(1),
                        resultSet.getDate(2).toString(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
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
            int res = connection.createStatement().executeUpdate("DELETE FROM appointment WHERE appointment_id=" + "'" + id + "'");
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
    public List<Appointment> getAll() {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        try {

            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM appointment");
            while(resultSet.next()){
                Appointment appointment = new Appointment(
                        resultSet.getString(1),
                        resultSet.getDate(2).toString(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                );
                appointmentArrayList.add(appointment);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return appointmentArrayList;
    }

    @Override
    public Appointment searchAppointmentBySIdAndNumber(String sessionId, Integer number) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM appointment WHERE session_id =" + "'" + sessionId + "'"+"AND number ="+ "'" + number + "'");
            if(resultSet.next()) {
                return new Appointment(
                        resultSet.getString(1),
                        resultSet.getDate(2).toString(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
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
