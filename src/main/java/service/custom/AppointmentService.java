package service.custom;

import dto.Appointment;
import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentService extends SuperService {

    boolean addAppointment(Appointment appointment) throws SQLException;

    boolean updateAppointment(Appointment appointment);

    Appointment searchAppointmentByName(String patientName);

    Appointment searchAppointmentByPhoneNo(String phoneNo);

    Appointment searchAppointmentById(String id);

    boolean deleteAppointment(String id);

    List<Appointment> getAll();

    String nextId();

    ObservableList getAppointmentStatus();

    String nextNumber(String sessionId);

    Appointment searchAppointmentBySIdAndNumber(String id, Integer number);
}
