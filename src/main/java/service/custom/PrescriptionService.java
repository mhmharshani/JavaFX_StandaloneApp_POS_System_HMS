package service.custom;

import dto.Prescription;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionService extends SuperService {

    boolean addPrescription(Prescription prescription) throws SQLException;

    boolean updatePrescription(Prescription prescription);

    Prescription searchPrescriptionById(String id);

    boolean deletePrescription(String id);

    List<Prescription> getAll();

    String nextId();
}
