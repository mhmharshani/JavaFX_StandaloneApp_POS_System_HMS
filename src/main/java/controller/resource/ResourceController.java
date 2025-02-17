package controller.resource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MedicalRecord;
import model.Room;

import java.util.List;

public class ResourceController implements ResourceService{

    @Override
    public boolean bookRoom(Room room) {

        return false;
    }

    @Override
    public boolean updateRoom(Room room) {
        return false;
    }

    @Override
    public Room searchRoomById(String id) {
        return null;
    }

    @Override
    public boolean deleteRoom(String id) {
        return false;
    }

    @Override
    public List<Room> getAllRooms() {
        return List.of();
    }

    @Override
    public boolean addMedicalRecord(MedicalRecord medicalRecord) {
        return false;
    }

    @Override
    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        return false;
    }

    @Override
    public MedicalRecord searchRecordById(String id) {
        return null;
    }

    @Override
    public boolean deleteMedicalRecord(String id) {
        return false;
    }

    @Override
    public List<MedicalRecord> getAllRecords() {
        return List.of();
    }

    public ObservableList loadRoom(){

        ObservableList<String> appointmentStatus = FXCollections.observableArrayList();
        appointmentStatus.add("Active");
        appointmentStatus.add("Cancel");
        appointmentStatus.add("Confirmed by Payment");

        return appointmentStatus;

    }
}
