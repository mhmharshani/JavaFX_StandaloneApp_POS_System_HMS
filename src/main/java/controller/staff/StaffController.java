package controller.staff;

import model.Employee;

import java.util.List;

public class StaffController implements StaffService {

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
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
        return null;
    }

    @Override
    public boolean deleteEmployee(String id) {
        return false;
    }

    @Override
    public List<Employee> getAll() {
        return List.of();
    }
}
