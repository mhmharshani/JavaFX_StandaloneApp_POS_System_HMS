package repository.custom.impl;

import db.DBConnection;
import dto.Billing;
import javafx.scene.control.Alert;
import repository.custom.PaymentDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public boolean save(Billing entity) throws SQLException {
        String SQL = "INSERT INTO billing VALUES (?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setDate(2, java.sql.Date.valueOf(entity.getGenDate()));
            pstm.setTime(3, java.sql.Time.valueOf(entity.getGenTime()));
            pstm.setString(4, entity.getDescription());
            pstm.setDouble(5, entity.getTotal());
            pstm.setString(6, entity.getStatus());
            pstm.setString(7, entity.getPatientId());

            Boolean isBillingAdded = pstm.executeUpdate() > 0;
            if(isBillingAdded){
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
    public boolean update(String s, Billing entity) {
        return false;
    }

    @Override
    public Billing search(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM billing WHERE billing_id =" + "'" + id + "'");
            if(resultSet.next()) {
                return new Billing(
                        resultSet.getString(1),
                        resultSet.getDate(2).toString(),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
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
            int res = connection.createStatement().executeUpdate("DELETE FROM billing WHERE billing_id=" + "'" + id + "'");
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
    public List<Billing> getAll() {
        ArrayList<Billing> billingArrayList = new ArrayList<>();
        try {

            ResultSet resultSet = DBConnection.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM billing");
            while(resultSet.next()){
                Billing billing = new Billing(
                        resultSet.getString(1),
                        resultSet.getDate(2).toString(),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
                );
                billingArrayList.add(billing);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return billingArrayList;
    }
}
