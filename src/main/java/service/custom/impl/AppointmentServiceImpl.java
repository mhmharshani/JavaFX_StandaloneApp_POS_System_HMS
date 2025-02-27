package service.custom.impl;

import dto.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.AppointmentDao;
import service.custom.AppointmentService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    AppointmentDao appointmentDao = DaoFactory.getInstance().getDaoType(DaoType.APPOINTMENT);

    @Override
    public boolean addAppointment(Appointment appointment) throws SQLException {
        return appointmentDao.save(appointment);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return false;
    }

    @Override
    public Appointment searchAppointmentByName(String patientName) {
        return null;
    }

    @Override
    public Appointment searchAppointmentByPhoneNo(String phoneNo) {
        return null;
    }

    @Override
    public Appointment searchAppointmentById(String id) {
        return appointmentDao.search(id);
    }

    @Override
    public boolean deleteAppointment(String id) {
        return appointmentDao.delete(id);
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentDao.getAll();
    }

    @Override
    public Appointment searchAppointmentBySIdAndNumber(String id, Integer number) {
        return appointmentDao.searchAppointmentBySIdAndNumber(id,number);
    }

    public String nextId(){
        List<Appointment> all = getAll();
        ArrayList<String> appointmentIds = new ArrayList<>();
        all.forEach(appointment ->{
            appointmentIds.add((appointment.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!appointmentIds.isEmpty()){

            for(int i=0;i< appointmentIds.size();i++){
                id=Integer.parseInt(appointmentIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLA#000001";
        }
        return String.format("HLA#%06d",max);
    }

    public ObservableList getAppointmentStatus(){

        ObservableList<String> appointmentStatus = FXCollections.observableArrayList();
        appointmentStatus.add("Active");
        appointmentStatus.add("Cancel");
        appointmentStatus.add("Confirmed by Payment");

        return appointmentStatus;

    }

    public String nextNumber(String sessionId){
        List<Appointment> all = getAll();
        ArrayList<Integer> numberList = new ArrayList<>();
        all.forEach(appointment ->{
            if(appointment.getSessionId().equals(sessionId)){
                numberList.add((appointment.getNumber()));
            }
        });

        int id;
        int max = 0;

        if(!numberList.isEmpty()){

            for(int i=0;i< numberList.size();i++){
                id=numberList.get(i);
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "001";
        }
        return String.format("%03d",max);
    }

}
