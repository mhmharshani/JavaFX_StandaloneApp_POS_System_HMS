package controller.payment;

import model.Billing;
import model.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {

    boolean addPayment(Billing billing) throws SQLException;

    boolean updatePayment(Billing billing);

    Billing searchPaymentById(String id);

    boolean deletePayment(String id);

    List<Billing> getAll();

}
