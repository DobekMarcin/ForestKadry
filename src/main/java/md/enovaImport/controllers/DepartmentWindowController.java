package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import md.enovaImport.modelsFX.DepartmentFX;
import md.enovaImport.modelsFX.PartsFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.jdbc.MSSQLDAO;
import md.enovaImport.sql.models.Department;
import md.enovaImport.utils.DialogUtils;

import java.sql.SQLException;
import java.util.List;

public class DepartmentWindowController {

    private final ObservableList<DepartmentFX> departmentFXObservableList = FXCollections.observableArrayList();
    @FXML
    private TableColumn distributorColumn;
    @FXML
    private TableView departmentTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn codeColumn;
    @FXML
    private TableColumn nameColumn;

    private MSSQLDAO mssqldao = new MSSQLDAO();
    private ImportDAO importDAO = new ImportDAO();

    public void initialize(){
        try {
            departmentFXObservableList.clear();
            List<Department> departmentList = importDAO.getDepartment();

            departmentList.forEach(element->{
                DepartmentFX departmentFX = new DepartmentFX();

                departmentFX.setId(element.getId());
                departmentFX.setCode(element.getCode());
                departmentFX.setName(element.getName());

                departmentFX.getDistributor().setText("Rozdzielnik");

                departmentFXObservableList.add(departmentFX);
            });

            idColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX,Integer>("id"));
            codeColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX,String>("code"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX,String>("name"));
            distributorColumn.setCellValueFactory(new PropertyValueFactory<DepartmentFX, Button>("distributor"));

            departmentTable.setItems(departmentFXObservableList);


        } catch (SQLException e) {
            DialogUtils.errorDialog("Błąd pobierania danych z enova.");
        }


    }
    public void updateButton() {

        try {
            List<Department> departmentList = mssqldao.getDepartment();

            departmentList.forEach(element->{

                try {
                    if(importDAO.checkdepartmentById(element.getId())>0){
                        importDAO.updatesDepartmentsById(element);
                    }else{
                    importDAO.addDepartment(element);
                        initialize();
                    }
                } catch (SQLException e) {
                    DialogUtils.errorDialog("Błąd pobierania danych.");
                }

            });
            DialogUtils.informationDialog("Zakończono aktualizację!");


        } catch (SQLException e) {
            DialogUtils.errorDialog("Błąd pobierania danych.");
        }

    }
}
