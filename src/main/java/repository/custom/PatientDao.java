package repository.custom;

import dto.Patient;
import repository.CrudDao;

public interface PatientDao extends CrudDao<Patient,String> {

    Patient searchPatientByPhoneNo(String phoneNo);

    Patient searchPatientByNIC(String nic);



}
