package repository.custom;

import dto.Patient;
import entity.PatientEntity;
import repository.CrudDao;

public interface PatientDao extends CrudDao<PatientEntity,String> {

    PatientEntity searchPatientByPhoneNo(String phoneNo);

    PatientEntity searchPatientByNIC(String nic);



}
