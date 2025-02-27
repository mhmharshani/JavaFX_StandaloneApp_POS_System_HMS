package service.custom.impl;

import db.DBConnection;
import dto.Doctor;
import dto.DoctorSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.DoctorDao;
import service.custom.DoctorService;
import util.DaoType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    DoctorDao doctorDao = DaoFactory.getInstance().getDaoType(DaoType.DOCTOR);

    @Override
    public boolean addDoctor(Doctor doctor) throws SQLException {
        return doctorDao.save(doctor);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public List<String> searchDoctorByName(String name) {
        return doctorDao.searchDoctorByName(name);
    }

    @Override
    public Doctor searchDoctorByPhoneNo(String phoneNo) {
        return null;
    }

    @Override
    public Doctor searchDoctorById(String id) {
        return doctorDao.search(id);
    }

    @Override
    public boolean deleteDoctor(String id) {
        return doctorDao.delete(id);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    public String nextId(){
        List<Doctor> all = getAll();
        ArrayList<String> doctorIds = new ArrayList<>();
        all.forEach(doctor ->{
            doctorIds.add((doctor.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!doctorIds.isEmpty()){

            for(int i=0;i< doctorIds.size();i++){
                id=Integer.parseInt(doctorIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLD#0001";
        }
        return String.format("HLD#%04d",max);
    }

    public ObservableList getDoctorId(){

        ObservableList doctorIds = FXCollections.observableArrayList();
        List<Doctor> all = getAll();

        all.forEach(doctor -> {
            doctorIds.add(doctor.getId());
        });
        return doctorIds;
    }

    public ObservableList getSpeciality(){

        ObservableList<String> specialityTypes = FXCollections.observableArrayList();
        List<Doctor> all = getAll();

        all.forEach(doctor -> {
            specialityTypes.add(doctor.getSpeciality());
        });
        return specialityTypes;
    }

    //-----------Doctor Session-------------------------

    public boolean addSession(DoctorSession doctorSession) throws SQLException {
        return doctorDao.saveSession(doctorSession);
    }

    public List<DoctorSession> getAllSessions() {
        return doctorDao.getAllSessions();
    }

    public List<DoctorSession> searchDoctorSessionByDocId(String id) {
        return doctorDao.searchDoctorSessionByDocId(id);
    }

    public DoctorSession searchDoctorSession (String doctorId, String date, String time) {
        return doctorDao.searchDoctorSession(doctorId,date,time);
    }

    public String nextSessionId(){
        List<DoctorSession> all = getAllSessions();
        ArrayList<String> sessionIds = new ArrayList<>();
        all.forEach( doctorSession ->{
            sessionIds.add((doctorSession.getId().split("#"))[1]);
        });

        int id;
        int max = 0;

        if(!sessionIds.isEmpty()){
            for(int i=0;i< sessionIds.size();i++){
                id=Integer.parseInt(sessionIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLS#000001";
        }
        return String.format("HLS#%06d",max);
    }

    public ObservableList getSessionNames(){

        ObservableList<String> sessionNames = FXCollections.observableArrayList();
        sessionNames.add("Late Night Session(1-4AM)");
        sessionNames.add("Early Morning Session(4-7AM)");
        sessionNames.add("Morning Session(7-10AM)");
        sessionNames.add("Mid-day Session(10AM-1PM)");
        sessionNames.add("Evening Session(1-7PM)");
        sessionNames.add("Night Session(7-12PM)");

        return sessionNames;

    }

    public ObservableList getSessionStatus(){

        ObservableList<String> sessionStatus = FXCollections.observableArrayList();
        sessionStatus.add("Active");
        sessionStatus.add("Cancel");
        sessionStatus.add("Pending");

        return sessionStatus;

    }


}
