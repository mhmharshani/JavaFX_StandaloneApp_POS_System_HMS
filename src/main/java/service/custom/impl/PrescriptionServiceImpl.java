package service.custom.impl;

import dto.Prescription;
import repository.DaoFactory;
import repository.custom.PrescriptionDao;
import service.custom.PrescriptionService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionServiceImpl implements PrescriptionService {

    PrescriptionDao prescriptionDao = DaoFactory.getInstance().getDaoType(DaoType.PRESCRIPTION);

    @Override
    public boolean addPrescription(Prescription prescription) throws SQLException {
        return prescriptionDao.save(prescription);
    }

    @Override
    public boolean updatePrescription(Prescription prescription) {
        return false;
    }

    @Override
    public Prescription searchPrescriptionById(String id) {
        return prescriptionDao.search(id);
    }

    @Override
    public boolean deletePrescription(String id) {
        return prescriptionDao.delete(id);
    }

    @Override
    public List<Prescription> getAll() {
        return prescriptionDao.getAll();
    }

    @Override
    public String nextId(){
        List<Prescription> all = getAll();
        ArrayList<String> prescriptionIds = new ArrayList<>();
        all.forEach(prescription ->{
            prescriptionIds.add((prescription.getId().split("#"))[1]);

        });

        int id;
        int max = 0;

        if(!prescriptionIds.isEmpty()){

            for(int i=0;i< prescriptionIds.size();i++){
                id=Integer.parseInt(prescriptionIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLPS#000001";
        }
        return String.format("HLPS#%06d",max);
    }
}
