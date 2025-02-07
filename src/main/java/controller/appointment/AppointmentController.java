package controller.appointment;
import controller.patient.PatientController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController implements AppointmentService{

    public String nextId(){
        List<Appointment> all = getAll();
        ArrayList<String> appointmentIds = new ArrayList<>();
        all.forEach(appointment ->{
            appointmentIds.add((appointment.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!appointmentIds.isEmpty()){

            for(int i=0;i< appointmentIds.size();i++){
                id=Integer.parseInt(appointmentIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLA#000001";
        }
        return String.format("HLA#%06d",max);
    }

    @Override
    public boolean addAppointment(Appointment appointment) {
        String SQL = "INSERT INTO appointment VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            pstm.setString(1, appointment.getId());
            pstm.setDate(2, java.sql.Date.valueOf(appointment.getDate()));
            pstm.setTime(3, java.sql.Time.valueOf(appointment.getTime()));
            pstm.setInt(4, appointment.getNumber());
            pstm.setString(5, appointment.getStatus());
            pstm.setString(6, appointment.getDoctorId());
            pstm.setString(7, appointment.getPatientId());
            pstm.setString(8,appointment.getSessionId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return false;
    }

    @Override
    public Appointment searchAppointmentByName(String patientName) {
        return null;
    }

    @Override
    public Appointment searchAppointmentByPhoneNo(String phoneNo) {
        return null;
    }

    @Override
    public Appointment searchAppointmentById(String id) {
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
    public boolean deleteAppointment(String id) {
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

    public ObservableList getAppointmentStatus(){

        ObservableList<String> appointmentStatus = FXCollections.observableArrayList();
        appointmentStatus.add("Active");
        appointmentStatus.add("Cancel");
        appointmentStatus.add("Confirmed by Payment");

        return appointmentStatus;

    }

    public String nextNumber(String sessionId){
        List<Appointment> all = getAll();
        ArrayList<Integer> numberList = new ArrayList<>();
        all.forEach(appointment ->{
            if(appointment.getSessionId().equals(sessionId)){
                numberList.add((appointment.getNumber()));
            }
        });

        int id;
        int max = 0;

        if(!numberList.isEmpty()){

            for(int i=0;i< numberList.size();i++){
                id=numberList.get(i);
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "001";
        }
        return String.format("%03d",max);
    }




}
