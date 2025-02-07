package controller.staff;

import controller.patient.PatientController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class StaffFormController implements Initializable {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colDesignation;

    @FXML
    private TableColumn colGender;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPhone;

    @FXML
    private TableColumn colQualification;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblStaff;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDesignation;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtQualifications;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(new StaffController().nextId());
    }

    @FXML
    void btnAddMemberOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtId.getText(),
                txtName.getText(),
                txtGender.getText(),
                txtAddress.getText(),
                txtPhone.getText(),
                txtDesignation.getText(),
                txtQualifications.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        try {
            Boolean isAdded = new StaffController().addEmployee(employee);
            Alert alert = isAdded ? (new Alert(Alert.AlertType.CONFIRMATION, "Added Success !!")) : (new Alert(Alert.AlertType.ERROR, "Added Failed !!"));
            alert.show();
            if (isAdded){
                txtId.setText(new StaffController().nextId());
                txtName.setText("");
                txtGender.setText("");
                txtAddress.setText("");
                txtPhone.setText("");
                txtDesignation.setText("");
                txtQualifications.setText("");
                txtSalary.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        Employee employee = new StaffController().searchEmployeeById(txtSearch.getText());
        if (employee !=null){
            boolean isDeleted = new StaffController().deleteEmployee(txtSearch.getText());
            if(isDeleted){
                txtId.setText(new StaffController().nextId());
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR, "No Employee Found !!").show();
        }
    }

    @FXML
    void btnEditOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Employee employee = new StaffController().searchEmployeeById(txtSearch.getText());
        if (employee!=null){
            loadTable(employee);
        }
        else {
            new Alert(Alert.AlertType.ERROR,"No Employee Found !!").show();
        }
    }

    @FXML
    void btnShowAllOnAction(ActionEvent event) {
        loadTable();

    }

    public void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        List<Employee> employeeArrayList = new StaffController().getAll();
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        employeeArrayList.forEach(patient -> {
            employeeObservableList.add(patient);
        });

        tblStaff.setItems(employeeObservableList);
    }

    public void loadTable(Employee employee){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();
        employeeObservableList.add(employee);

        tblStaff.setItems(employeeObservableList);
    }

}
