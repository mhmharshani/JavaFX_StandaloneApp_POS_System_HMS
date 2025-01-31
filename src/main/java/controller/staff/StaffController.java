package controller.staff;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffController implements StaffService {

    public String nextId(){
        List<Employee> all = getAll();
        ArrayList<String> employeeIds = new ArrayList<>();
        all.forEach(employee ->{
            employeeIds.add((employee.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!employeeIds.isEmpty()){

            for(int i=0;i< employeeIds.size();i++){
                id=Integer.parseInt(employeeIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLE#0001";
        }
        return String.format("HLE#%04d",max);
    }

    @Override
    public boolean addEmployee(Employee employee) {
        String SQL = "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(SQL);
            pstm.setString(1, employee.getId());
            pstm.setString(2, employee.getName());
            pstm.setString(3, employee.getGender());
            pstm.setString(4, employee.getAddress());
            pstm.setString(5, employee.getPhoneNo());
            pstm.setString(6, employee.getDesignation());
            pstm.setString(7, employee.getQualification());
            pstm.setDouble(8, employee.getSalary());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return false;
    }

    @Override
    public Employee searchEmployeeByName(String name) {
        return null;
    }

    @Override
    public Employee searchEmployeeByPhoneNo(String phoneNo) {
        return null;
    }

    @Override
    public Employee searchEmployeeById(String id) {
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
    public boolean deleteEmployee(String id) {
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
