package controller.resource;

import model.MedicalRecord;
import model.Room;
import java.util.List;

public interface ResourceService {

    boolean bookRoom(Room room);

    boolean updateRoom(Room room);

    Room searchRoomById(String id);

    boolean deleteRoom(String id);

    List<Room> getAllRooms();

    boolean addMedicalRecord(MedicalRecord medicalRecord);

    boolean updateMedicalRecord(MedicalRecord medicalRecord);

    MedicalRecord searchRecordById(String id);

    boolean deleteMedicalRecord(String id);

    List<MedicalRecord> getAllRecords();

}
