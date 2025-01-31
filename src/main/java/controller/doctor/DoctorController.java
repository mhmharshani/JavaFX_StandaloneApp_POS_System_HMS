package controller.doctor;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Doctor;
import model.Employee;

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
    public boolean addDoctor(Doctor doctor) {
        String SQL = "INSERT INTO doctor VALUES (?,?,?,?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            pstm.setString(1, doctor.getId());
            pstm.setString(2, doctor.getSpeciality());
            pstm.setString(3, doctor.getAvailability());
            pstm.setString(4, doctor.getEmpId());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
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
}
