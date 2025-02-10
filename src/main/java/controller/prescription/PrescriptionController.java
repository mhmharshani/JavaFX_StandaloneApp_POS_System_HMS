package controller.prescription;

import model.Prescription;

import java.util.List;

public class PrescriptionController implements PrescriptionService{

    @Override
    public boolean addPrescription(Prescription prescription) {
        return false;
    }

    @Override
    public boolean updatePrescription(Prescription prescription) {
        return false;
    }

    @Override
    public Prescription searchPrescriptionById(String id) {
        return null;
    }

    @Override
    public boolean deletePrescription(String id) {
        return false;
    }

    @Override
    public List<Prescription> getAll() {
        return List.of();
    }
}
