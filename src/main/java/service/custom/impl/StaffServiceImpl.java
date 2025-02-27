package service.custom.impl;

import db.DBConnection;
import dto.Employee;
import javafx.scene.control.Alert;
import repository.DaoFactory;
import repository.custom.StaffDao;
import service.custom.StaffService;
import util.DaoType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffServiceImpl implements StaffService {

    StaffDao staffDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public boolean addEmployee(Employee employee) throws SQLException {
        return staffDao.save(employee);
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
        return staffDao.search(id);
    }

    @Override
    public boolean deleteEmployee(String id) {
        return staffDao.delete(id);
    }

    @Override
    public List<Employee> getAll() {
        return staffDao.getAll();
    }

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
}
