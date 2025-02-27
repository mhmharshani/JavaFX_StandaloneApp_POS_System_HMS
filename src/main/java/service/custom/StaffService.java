package service.custom;

import dto.Employee;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface StaffService extends SuperService {

    boolean addEmployee(Employee employee) throws SQLException;

    boolean updateEmployee(Employee employee);

    Employee searchEmployeeByName(String name);

    Employee searchEmployeeByPhoneNo(String phoneNo);

    Employee searchEmployeeById(String id);

    boolean deleteEmployee(String id);

    List<Employee> getAll();

    String nextId();
}
