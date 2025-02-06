package controller.patient;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController implements PatientService{

    @Override
    public boolean addPatient(Patient patient) {
        String SQL = "INSERT INTO patient VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            pstm.setString(1, patient.getId());
            pstm.setString(2, patient.getName());
            pstm.setString(3, patient.getNic());
            pstm.setString(4, patient.getAddress());
            pstm.setString(5, patient.getGender());
            pstm.setString(6, patient.getPhoneNo());
            pstm.setInt(7, patient.getAge());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public String nextId(){
        List<Patient> all = getAll();
        ArrayList<String> patientIds = new ArrayList<>();
        all.forEach(patient ->{
            patientIds.add((patient.getId().split("#"))[1]);

            });

        int id;
        int max = Integer.parseInt(patientIds.get(0));

        if(!patientIds.isEmpty()){

            for(int i=0;i< patientIds.size();i++){
                id=Integer.parseInt(patientIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLP#0001";
        }
        return String.format("HLP#%04d",max);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return false;
    }

    @Override
    public Patient searchPatientByNIC(String nic) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE nic=" + "'" + nic + "'");
            if(resultSet.next()) {
                return new Patient(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
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
    public Patient searchPatientByPhoneNo(String phoneNo) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE phoneNo=" + "'" + phoneNo + "'");
            if(resultSet.next()) {
                return new Patient(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
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
    public Patient searchPatientById(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE patient_id=" + "'" + id + "'");
            if(resultSet.next()) {
                return new Patient(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
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
    public boolean deletePatient(String nic) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            int res = connection.createStatement().executeUpdate("DELETE FROM patient WHERE nic=" + "'" + nic + "'");
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
    public List<Patient> getAll() {
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");

            while(resultSet.next()){
                Patient patient = new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getInt(7)
                );
                patientArrayList.add(patient);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return patientArrayList;
    }

    public ObservableList getPatientId(){

        ObservableList<String> patientIds = FXCollections.observableArrayList();
        List<Patient> all = getAll();

        all.forEach(patient -> {
            patientIds.add(patient.getId());
        });
        return patientIds;
    }


}
