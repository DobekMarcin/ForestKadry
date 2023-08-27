package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import md.enovaImport.modelsFX.PodatkiSkladkiFX;
import md.enovaImport.modelsFX.WyplataFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.PodatkiSkladki;
import md.enovaImport.xml.models.Wyplata;

import java.sql.SQLException;
import java.util.List;

public class TaxWindowController {

    private final ObservableList<PodatkiSkladkiFX> podatkiSkladkiFXObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView taxTable;
    @FXML
    private TableColumn taxTableId;
    @FXML
    private TableColumn taxTableAdvance;
    @FXML
    private TableColumn taxTableRetirementWorker;
    @FXML
    private TableColumn taxTableEmployeePension;
    @FXML
    private TableColumn taxTableSicknessEmployee;
    @FXML
    private TableColumn taxTableResultantWorker;
    @FXML
    private TableColumn taxTableRetairmentCompany;
    @FXML
    private TableColumn taxTablePensionCompany;
    @FXML
    private TableColumn taxTableSicknessCompany;
    @FXML
    private TableColumn taxTableResultantCompany;
    @FXML
    private TableColumn taxTableHealtWorker;
    @FXML
    private TableColumn taxTableFp;
    @FXML
    private TableColumn taxTableFGSP;
    @FXML
    private TableColumn taxTableFep;
    @FXML
    private TableColumn taxTablePPKEmployee;
    @FXML
    private TableColumn taxTablePPKCompany;
    private Integer importId;
    private Integer listId;

    private Integer paymentId;
    private Integer elementId=0;

    private MainWindowController mainWindowController;

    private final ImportDAO importDAO = new ImportDAO();



    public void initialize() {


        if (elementId != 0) {

            List<PodatkiSkladki> podatkiSkladkiList = null;
            try {
                podatkiSkladkiList = importDAO.getTaxListById(importId, listId, paymentId, elementId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            podatkiSkladkiList.forEach(element -> {
                PodatkiSkladkiFX podatkiSkladkiFX = new PodatkiSkladkiFX();

                podatkiSkladkiFX.setIdPodatku(element.getIdPodatku());
                podatkiSkladkiFX.setPodatekZaliczkaUS(element.getPodatekZaliczkaUS());
                podatkiSkladkiFX.setEmerytalnaPracownik(element.getEmerytalnaPracownik());
                podatkiSkladkiFX.setRentowaPracownik(element.getRentowaPracownik());
                podatkiSkladkiFX.setChorobowaPracownik(element.getChorobowaPracownik());
                podatkiSkladkiFX.setWypadkowaPracownik(element.getWypadkowaPracownik());
                podatkiSkladkiFX.setEmerytalnaFirma(element.getEmerytalnaFirma());
                podatkiSkladkiFX.setRentowaFirma(element.getRentowaFirma());
                podatkiSkladkiFX.setChorobowaFirma(element.getChorobowaFirma());
                podatkiSkladkiFX.setWypadkowaFirma(element.getWypadkowaFirma());
                podatkiSkladkiFX.setZdrowotnaPracownik(element.getZdrowotnaPracownik());
                podatkiSkladkiFX.setFP(element.getFP());
                podatkiSkladkiFX.setFGSP(element.getFGSP());
                podatkiSkladkiFX.setFEP(element.getFEP());
                podatkiSkladkiFX.setPPKPracownik(element.getPPKPracownik());
                podatkiSkladkiFX.setPPKFirma(element.getPPKFirma());

                podatkiSkladkiFXObservableList.add(podatkiSkladkiFX);

            });

            taxTableId.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Integer>("idPodatku"));
            taxTableAdvance.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("podatekZaliczkaUS"));
            taxTableRetirementWorker.setCellValueFactory((new PropertyValueFactory<PodatkiSkladki,Double>("emerytalnaPracownik")));
            taxTableEmployeePension.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("rentowaPracownik"));
            taxTableSicknessEmployee.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("chorobowaPracownik"));
            taxTableResultantWorker.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("wypadkowaPracownik"));
            taxTableRetairmentCompany.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("emerytalnaFirma"));
            taxTablePensionCompany.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("rentowaFirma"));
            taxTableSicknessCompany.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("chorobowaFirma"));
            taxTableResultantCompany.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("wypadkowaFirma"));
            taxTableHealtWorker.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("zdrowotnaPracownik"));
            taxTableFp.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("FP"));
            taxTableFGSP.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("FGSP"));
            taxTableFep.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("FEP"));
            taxTablePPKEmployee.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("PPKPracownik"));
            taxTablePPKCompany.setCellValueFactory(new PropertyValueFactory<PodatkiSkladki,Double>("PPKFirma"));

            taxTable.setItems(podatkiSkladkiFXObservableList);

            taxTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
        }
    }

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void backButton() {
        mainWindowController.setElementWindow();
    }

    public ObservableList<PodatkiSkladkiFX> getPodatkiSkladkiFXObservableList() {
        return podatkiSkladkiFXObservableList;
    }
}
