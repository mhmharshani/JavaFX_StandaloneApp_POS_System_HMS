package service.custom;

import dto.MedicalRecord;
import dto.Room;
import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface ResourceService extends SuperService {

    boolean bookRoom(String id,Room room) throws SQLException;

    boolean updateRoom(Room room);

    Room searchRoomById(String id);

    boolean deleteRoom(String id);

    List<Room> getAllRooms();

    boolean addMedicalRecord(MedicalRecord medicalRecord) throws SQLException;

    boolean updateMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord searchRecordById(String id);

    boolean deleteMedicalRecord(String id);

    List<MedicalRecord> getAllRecords();

    ObservableList loadRoomTypes();

    ObservableList loadRoomCapacity();

    String nextId();

}
