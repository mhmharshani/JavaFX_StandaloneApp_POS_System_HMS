package controller.prescription;

import model.Billing;
import model.Prescription;

import java.sql.SQLException;
import java.util.List;

public interface PrescriptionService {

    boolean addPrescription(Prescription prescription);

    boolean updatePrescription(Prescription prescription);

    Prescription searchPrescriptionById(String id);

    boolean deletePrescription(String id);

    List<Prescription> getAll();

}
