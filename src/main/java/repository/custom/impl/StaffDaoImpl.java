package repository.custom.impl;

import db.DBConnection;
import dto.Employee;
import javafx.scene.control.Alert;
import repository.custom.StaffDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
    @Override
    public boolean save(Employee entity) throws SQLException {
        String SQL = "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pstm = connection.prepareStatement(SQL);
            pstm.setString(1, entity.getId());
            pstm.setString(2, entity.getName());
            pstm.setString(3, entity.getGender());
            pstm.setString(4, entity.getAddress());
            pstm.setString(5, entity.getPhoneNo());
            pstm.setString(6, entity.getDesignation());
            pstm.setString(7, entity.getQualification());
            pstm.setDouble(8, entity.getSalary());
            Boolean isEmployeeAdded = pstm.executeUpdate() > 0;
            if (isEmployeeAdded){
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
    public boolean update(String s, Employee entity) {
        return false;
    }

    @Override
    public Employee search(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM employee WHERE employee_id=" + "'" + id + "'");
            if(resultSet.next()) {
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8)
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
            int res = connection.createStatement().executeUpdate("DELETE FROM employee WHERE employee_id=" + "'" + id + "'");
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
    public List<Employee> getAll() {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");

            while(resultSet.next()){
                Employee employee = new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getDouble(8)
                );
                employeeArrayList.add(employee);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return employeeArrayList;
    }
}
