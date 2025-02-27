package repository.custom;

import dto.Appointment;
import repository.CrudDao;

public interface AppointmentDao extends CrudDao<Appointment,String> {

    Appointment searchAppointmentBySIdAndNumber(String sessionId, Integer number);

}
