package repository.custom.impl;

import db.DBConnection;
import dto.Prescription;
import javafx.scene.control.Alert;
import repository.custom.PrescriptionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDaoImpl implements PrescriptionDao {


    @Override
    public boolean save(Prescription entity) throws SQLException {
        String SQL = "INSERT INTO prescription VALUES (?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setString(2, entity.getDiagnosis());
            pstm.setString(3, entity.getMedicine());
            pstm.setString(4, entity.getDosage());
            pstm.setString(5, entity.getDuration());
            pstm.setString(6, entity.getDoctorId());
            pstm.setString(7, entity.getPatientId());

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
    public boolean update(String s, Prescription entity) {
        return false;
    }

    @Override
    public Prescription search(String id) {
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
    public boolean delete(String id) {
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
}
