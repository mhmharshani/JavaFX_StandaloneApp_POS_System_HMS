package controller.prescription;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Appointment;
import model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionController implements PrescriptionService{

    @Override
    public boolean addPrescription(Prescription prescription) throws SQLException {
        String SQL = "INSERT INTO prescription VALUES (?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, prescription.getId());
            pstm.setString(2, prescription.getDiagnosis());
            pstm.setString(3, prescription.getMedicine());
            pstm.setString(4, prescription.getDosage());
            pstm.setString(5, prescription.getDuration());
            pstm.setString(6, prescription.getDoctorId());
            pstm.setString(7, prescription.getPatientId());

            Boolean isPrescriptionAdded = pstm.executeUpdate() > 0;
            if(isPrescriptionAdded){
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
    public boolean updatePrescription(Prescription prescription) {
        return false;
    }

    @Override
    public Prescription searchPrescriptionById(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM prescription WHERE prescription_id =" + "'" + id + "'");
            if(resultSet.next()) {
                return new Prescription(
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

    @Override
    public boolean deletePrescription(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            int res = connection.createStatement().executeUpdate("DELETE FROM prescription WHERE prescription_id =" + "'" + id + "'");
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
    public List<Prescription> getAll() {
        ArrayList<Prescription> prescriptionsArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM prescription");
            while(resultSet.next()){
                Prescription prescription = new Prescription(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                prescriptionsArrayList.add(prescription);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return prescriptionsArrayList;
    }

    public String nextId(){
        List<Prescription> all = getAll();
        ArrayList<String> prescriptionIds = new ArrayList<>();
        all.forEach(prescription ->{
            prescriptionIds.add((prescription.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!prescriptionIds.isEmpty()){

            for(int i=0;i< prescriptionIds.size();i++){
                id=Integer.parseInt(prescriptionIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLPS#000001";
        }
        return String.format("HLPS#%06d",max);
    }
}
