package service;

import repository.custom.impl.StaffDaoImpl;
import service.custom.PaymentService;
import service.custom.impl.*;
import util.ServiceType;

public class ServiceFactory {

    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){

        return instance==null?instance=new ServiceFactory():instance;
    }
    public <T extends SuperService> T getServiceType(ServiceType type){
        switch (type){
            case PATIENT: return (T) new PatientServiceImpl();
            case DOCTOR: return (T) new DoctorServiceImpl();
            case EMPLOYEE: return (T) new StaffServiceImpl();
            case APPOINTMENT: return (T) new AppointmentServiceImpl();
            case BILLING: return (T) new PaymentServiceImpl();
            case PRESCRIPTION: return (T) new PrescriptionServiceImpl();
            case ROOM: return (T) new ResourceServiceImpl();
            case CHART:return (T) new StatisticsServiceImpl();
        }
        return null;
    }
}
