package service.custom.impl;

import dto.Billing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.PaymentDao;
import service.custom.PaymentService;
import util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    PaymentDao paymentDao = DaoFactory.getInstance().getDaoType(DaoType.BILLING);

    @Override
    public boolean addPayment(Billing billing) throws SQLException {
        return paymentDao.save(billing);
    }

    @Override
    public boolean updatePayment(Billing billing) {
        return false;
    }

    @Override
    public Billing searchPaymentById(String id) {
        return paymentDao.search(id);
    }

    @Override
    public boolean deletePayment(String id) {
        return paymentDao.delete(id);
    }

    @Override
    public List<Billing> getAll() {
        return paymentDao.getAll();
    }

    @Override
    public String nextId() {
        List<Billing> all = getAll();
        ArrayList<String> billingIds = new ArrayList<>();
        all.forEach(billing ->{
            billingIds.add((billing.getId().split("#"))[1]);
        });

        int id;
        int max = 0;

        if(!billingIds.isEmpty()){
            for(int i=0;i< billingIds.size();i++){
                id=Integer.parseInt(billingIds.get(i));
                if(max<id){
                    max=id;
                }
            }
            max++;
        }
        else {
            return "HLB#000001";
        }
        return String.format("HLB#%06d",max);
    }

    @Override
    public ObservableList getPaymentDesc() {
        ObservableList<String> paymentDesc = FXCollections.observableArrayList();
        paymentDesc.add("Channeling");
        paymentDesc.add("ChannelingByCenter");
        paymentDesc.add("OPD Charges");
        paymentDesc.add("Scanning");
        paymentDesc.add("Testing");

        return paymentDesc;
    }
}
