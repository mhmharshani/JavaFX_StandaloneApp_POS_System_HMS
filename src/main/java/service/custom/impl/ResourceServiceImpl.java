package service.custom.impl;

import dto.Appointment;
import dto.MedicalRecord;
import dto.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import repository.DaoFactory;
import repository.custom.ResourceDao;
import service.custom.ResourceService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {

    ResourceDao resourceDao = DaoFactory.getInstance().getDaoType(DaoType.ROOM);

    @Override
    public boolean bookRoom(String id,Room room) throws SQLException {
        return resourceDao.save(room);
    }

    @Override
    public boolean updateRoom(Room room) {
        return false;
    }

    @Override
    public Room searchRoomById(String id) {
        return resourceDao.search(id);
    }

    @Override
    public boolean deleteRoom(String id) {
        boolean isDeleted = resourceDao.delete(id);
        if(isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Success !!").show();
            return true;
        }
        return false;
    }

    @Override
    public List<Room> getAllRooms() {
        return resourceDao.getAll();
    }

    @Override
    public boolean addMedicalRecord(MedicalRecord medicalRecord) throws SQLException {
        return resourceDao.addMedicalRecord(medicalRecord);
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
        return resourceDao.getAllRecords();
    }

    public ObservableList loadRoomTypes(){

        ObservableList<String> roomTypeList = FXCollections.observableArrayList();
        roomTypeList.add("Luxury");
        roomTypeList.add("Semi-Luxury");
        roomTypeList.add("Normal");

        return roomTypeList;

    }

    public ObservableList loadRoomCapacity(){

        ObservableList<String> roomCapacityList = FXCollections.observableArrayList();
        roomCapacityList.add("1 Person");
        roomCapacityList.add("2 Person");
        roomCapacityList.add("3 Person");
        roomCapacityList.add("4 Person");

        return roomCapacityList;

    }
    public String nextId(){
        List<MedicalRecord> all = getAllRecords();
        ArrayList<String> recordIds = new ArrayList<>();
        all.forEach(medicalRecord ->{
            recordIds.add((medicalRecord.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!recordIds.isEmpty()){

            for(int i=0;i< recordIds.size();i++){
                id=Integer.parseInt(recordIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLRD#000001";
        }
        return String.format("HLRD#%06d",max);
    }
}
