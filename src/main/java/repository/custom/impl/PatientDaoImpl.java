package repository.custom.impl;

import config.HibernateConfig;
import db.DBConnection;
import entity.PatientEntity;
import javafx.scene.control.Alert;
import dto.Patient;
import org.hibernate.Session;
import repository.custom.PatientDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao {

    @Override
    public boolean save(PatientEntity entity) throws SQLException {
//        String SQL = "INSERT INTO patient VALUES (?,?,?,?,?,?,?)";
//        Connection connection = DBConnection.getInstance().getConnection();
//        try {
//            connection.setAutoCommit(false);
//            PreparedStatement pstm = connection.prepareStatement(SQL);
//            pstm.setString(1, entity.getId());
//            pstm.setString(2, entity.getName());
//            pstm.setString(3, entity.getNic());
//            pstm.setString(4, entity.getAddress());
//            pstm.setString(5, entity.getGender());
//            pstm.setString(6, entity.getPhoneNo());
//            pstm.setInt(7, entity.getAge());
//            boolean isPatientAdded = pstm.executeUpdate() > 0;
//            if(isPatientAdded){
//                connection.commit();
//                return true;
//            }
//        }finally {
//            connection.setAutoCommit(true);
//        }
//        connection.rollback();
//        return false;

        //---------------Using Hibernate to generate SQL Queries ------------------------
        Session session = HibernateConfig.getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(String s, PatientEntity entity) {
        return false;
    }

    @Override
    public PatientEntity search(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE patient_id=" + "'" + id + "'");
            if(resultSet.next()) {
                return new PatientEntity(
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
    public boolean delete(String nic) {
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
    public List<PatientEntity> getAll() {
        ArrayList<PatientEntity> patientArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM patient");

            while(resultSet.next()){
                PatientEntity patient = new PatientEntity(
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

    @Override
    public PatientEntity searchPatientByPhoneNo(String phoneNo) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE phoneNo=" + "'" + phoneNo + "'");
            if(resultSet.next()) {
                return new PatientEntity(
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
    public PatientEntity searchPatientByNIC(String nic) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM patient WHERE nic=" + "'" + nic + "'");
            if(resultSet.next()) {
                return new PatientEntity(
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
}
