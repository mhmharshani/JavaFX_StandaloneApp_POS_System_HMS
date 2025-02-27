package repository.custom;

import dto.MedicalRecord;
import dto.Room;
import repository.CrudDao;

import java.sql.SQLException;
import java.util.List;

public interface ResourceDao extends CrudDao<Room,String> {

    boolean addMedicalRecord(MedicalRecord medicalRecord) throws SQLException;

    List<MedicalRecord> getAllRecords();
}
