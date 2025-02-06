package controller.appointment;
import controller.patient.PatientController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController implements AppointmentService{

    public String nextId(){
        List<Appointment> all = getAll();
        ArrayList<String> appointmentIds = new ArrayList<>();
        all.forEach(doctor ->{
            appointmentIds.add((doctor.getId().split("#"))[1]);

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
//        String SQL = "INSERT INTO appointment VALUES (?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
//            pstm.setString(1, appointment.getId());
//            pstm.setDate(2, appointment.getDate());
//            pstm.setString(3, appointment.getTime());
//            pstm.setInt(4, appointment.getNumber());
//            pstm.setString(5, appointment.getStatus());
//            pstm.setString(6, appointment.getDoctorId());
//            pstm.setString(7, appointment.getPatientId());
//            pstm.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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
        return null;
    }

    @Override
    public boolean deleteAppointment(String id) {
        return false;
    }

    @Override
    public List<Appointment> getAll() {
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        try {

            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM appointment");
            while(resultSet.next()){
                Appointment appointment = new Appointment(
                        resultSet.getString(1),
                        resultSet.getDate(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                appointmentArrayList.add(appointment);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return appointmentArrayList;
    }


}
