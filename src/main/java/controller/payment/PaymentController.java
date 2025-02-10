package controller.payment;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;
import model.Billing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentController implements PaymentService {

    public String nextId(){
        List<Billing> all = getAll();
        ArrayList<String> billingIds = new ArrayList<>();
        all.forEach(billing ->{
            billingIds.add((billing.getId().split("#"))[1]);
        });

        int id;
        int max = 0;

        if(!billingIds.isEmpty()){
            for(int i=0;i< billingIds.size();i++){
                id=Integer.parseInt(billingIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLB#000001";
        }
        return String.format("HLB#%06d",max);
    }

    @Override
    public boolean addPayment(Billing billing) throws SQLException {
        String SQL = "INSERT INTO billing VALUES (?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, billing.getId());
            pstm.setDate(2, java.sql.Date.valueOf(billing.getGenDate()));
            pstm.setTime(3, java.sql.Time.valueOf(billing.getGenTime()));
            pstm.setString(4, billing.getDescription());
            pstm.setDouble(5, billing.getTotal());
            pstm.setString(6, billing.getStatus());
            pstm.setString(7, billing.getPatientId());

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
    public boolean updatePayment(Billing billing) {
        return false;
    }

    @Override
    public Billing searchPaymentById(String id) {
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
    public boolean deletePayment(String id) {
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

    public ObservableList getPaymentDesc(){

        ObservableList<String> paymentDesc = FXCollections.observableArrayList();
        paymentDesc.add("Channeling");
        paymentDesc.add("ChannelingByCenter");
        paymentDesc.add("OPD Charges");
        paymentDesc.add("Scanning");
        paymentDesc.add("Testing");

        return paymentDesc;

    }
}
