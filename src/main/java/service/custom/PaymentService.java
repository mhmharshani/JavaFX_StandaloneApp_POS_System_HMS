package service.custom;

import dto.Billing;
import javafx.collections.ObservableList;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService extends SuperService {

    boolean addPayment(Billing billing) throws SQLException;

    boolean updatePayment(Billing billing);

    Billing searchPaymentById(String id);

    boolean deletePayment(String id);

    List<Billing> getAll();

    String nextId();

    ObservableList getPaymentDesc();
}
