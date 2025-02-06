package controller.appointment;

import model.Appointment;
import java.util.List;

public interface AppointmentService {


    boolean addAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    Appointment searchAppointmentByName(String patientName);

    Appointment searchAppointmentByPhoneNo(String phoneNo);

    Appointment searchAppointmentById(String id);

    boolean deleteAppointment(String id);

    List<Appointment> getAll();



}
