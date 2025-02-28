package service.custom.impl;

import com.google.inject.Inject;
import entity.PatientEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dto.Patient;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.PatientDao;
import service.custom.PatientService;
import util.DaoType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    @Inject
    PatientDao patientDao;

    @Override
    public boolean addPatient(Patient patient) throws SQLException {
        PatientEntity map = new ModelMapper().map(patient, PatientEntity.class);
        return patientDao.save(map);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        return false;
    }

    @Override
    public Patient searchPatientByNIC(String nic) {
        Patient map = new ModelMapper().map(patientDao.searchPatientByNIC(nic), Patient.class);
        return map;
    }

    @Override
    public Patient searchPatientByPhoneNo(String phoneNo) {
        Patient map = new ModelMapper().map(patientDao.searchPatientByPhoneNo(phoneNo), Patient.class);
        return map;
    }

    @Override
    public Patient searchPatientById(String id) {
        Patient map = new ModelMapper().map(patientDao.search(id), Patient.class);
        return map;
    }

    @Override
    public boolean deletePatient(String nic) {
        return patientDao.delete(nic);
    }

    @Override
    public List<Patient> getAll() {
        List<PatientEntity> allPatientEntity = patientDao.getAll();
        ArrayList<Patient> allPatient = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        allPatientEntity.forEach(patientEntity->{
            allPatient.add(modelMapper.map(patientEntity, Patient.class));
        });
        return allPatient;
    }

    public String nextId(){
        List<Patient> all = getAll();
        ArrayList<String> patientIds = new ArrayList<>();
        all.forEach(patient ->{
            patientIds.add((patient.getPatient_id().split("#"))[1]);

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
            patientIds.add(patient.getPatient_id());
        });
        return patientIds;
    }
}
