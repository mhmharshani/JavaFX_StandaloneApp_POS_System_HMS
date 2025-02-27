package service.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.Patient;
import repository.DaoFactory;
import repository.custom.PatientDao;
import service.custom.PatientService;
import util.DaoType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    PatientDao patientDao = DaoFactory.getInstance().getDaoType(DaoType.PATIENT);

    @Override
    public boolean addPatient(Patient patient) throws SQLException {
        return patientDao.save(patient);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return false;
    }

    @Override
    public Patient searchPatientByNIC(String nic) {
        return patientDao.searchPatientByNIC(nic);
    }

    @Override
    public Patient searchPatientByPhoneNo(String phoneNo) {
        return patientDao.searchPatientByPhoneNo(phoneNo);
    }

    @Override
    public Patient searchPatientById(String id) {
        return patientDao.search(id);
    }

    @Override
    public boolean deletePatient(String nic) {
        return patientDao.delete(nic);
    }

    @Override
    public List<Patient> getAll() {
        return patientDao.getAll();
    }

    public String nextId(){
        List<Patient> all = getAll();
        ArrayList<String> patientIds = new ArrayList<>();
        all.forEach(patient ->{
            patientIds.add((patient.getId().split("#"))[1]);

        });

        int id;
        int max = Integer.parseInt(patientIds.get(0));

        if(!patientIds.isEmpty()){

            for(int i=0;i< patientIds.size();i++){
                id=Integer.parseInt(patientIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLP#0001";
        }
        return String.format("HLP#%04d",max);
    }

    public ObservableList getPatientId(){

        ObservableList<String> patientIds = FXCollections.observableArrayList();
        List<Patient> all = getAll();

        all.forEach(patient -> {
            patientIds.add(patient.getId());
        });
        return patientIds;
    }
}
