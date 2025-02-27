package repository;

import dto.Employee;
import repository.custom.impl.*;
import util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type){
        switch (type){
            case PATIENT: return (T) new PatientDaoImpl();
            case DOCTOR: return (T) new DoctorDaoImpl();
            case EMPLOYEE: return (T) new StaffDaoImpl();
            case APPOINTMENT: return (T) new AppointmentDaoImpl();
            case BILLING: return (T) new PaymentDaoImpl();
            case PRESCRIPTION: return (T) new PrescriptionDaoImpl();
            case ROOM: return (T) new ResourceDaoImpl();
        }
        return null;
    }

}
