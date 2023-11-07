package md.enovaImport.controllers;

import com.sun.javafx.scene.layout.ScaledMath;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import md.enovaImport.modelsFX.*;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.jdbc.PostgreSQLDAO;
import md.enovaImport.sql.models.*;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.PodatkiSkladki;

import javax.mail.Part;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.javafx.scene.layout.ScaledMath.round;

public class ListPatternWindowController {

    private final ObservableList<PayListPatternFX> payListPatternFXES = FXCollections.observableArrayList();
    private final ObservableList<ImportModelFX> importModelFXES = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    @FXML
    private TableColumn listTableDescriptionList;
    @FXML
    private TableColumn listTableImportId;
    @FXML
    private TableColumn listTableListId;
    @FXML
    private TableColumn listTableListName;
    @FXML
    private TableColumn listTableListDateColumn;
    @FXML
    private TableColumn listTableCodeList;
    @FXML
    private TableColumn listTableAgree;
    @FXML
    private TableColumn listTableComboBox;
    @FXML
    private TableColumn listTableBookButton;
    @FXML
    private TableView listTable;
    @FXML
    private ComboBox importComboBox;
    private List<ImportModel> importList;
    private PostgreSQLDAO postgreSQLDAO = new PostgreSQLDAO();

    public void initialize() {
        try {
            importList = importDAO.getImportList();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        importList.forEach(element -> {
            ImportModelFX importModelFX = new ImportModelFX();
            importModelFX.setId(element.getId());
            importModelFX.setOpis(element.getOpis());
            importModelFX.setData(element.getDataImportu().toString());
            importModelFXES.add(importModelFX);
        });

        importComboBox.getItems().addAll(importModelFXES);

        importComboBox.setCellFactory(cell -> new ListCell<ImportModelFX>() {
            final GridPane gridPane = new GridPane();
            final Label labelId = new Label();
            final Label labelOpis = new Label();
            final Label labelData = new Label();

            {
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(10, 30, 10), new ColumnConstraints(200, 200, 100));
                gridPane.add(labelId, 0, 1);
                gridPane.add(labelOpis, 1, 1);
            }

            @Override
            protected void updateItem(ImportModelFX importModelFX, boolean b) {
                super.updateItem(importModelFX, b);

                if (!b && importModelFX != null) {
                    labelId.setText(importModelFX.getId() + ".");
                    labelOpis.setText(importModelFX.getOpis());
                    setGraphic(gridPane);
                } else {
                    setGraphic(null);
                }
            }
        });
    }

    public void initializeComboBox(PayListPatternFX payListPatternFX) {
        ComboBox comboBox = payListPatternFX.getBookKeepingPatternsComboBox();
        ObservableList<BookKeepingPatternsFX> bookKeepingPatternsFXES = FXCollections.observableArrayList();
        List<BookKeepingPatterns> bookKeepingPatterns = new ArrayList<>();
        try {
            bookKeepingPatterns = importDAO.getBookKeepingPatterns();
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        bookKeepingPatterns.forEach(element -> {
            BookKeepingPatternsFX bookKeepingPatternsFX = new BookKeepingPatternsFX();
            bookKeepingPatternsFX.setId(element.getId());
            bookKeepingPatternsFX.setPatternName(element.getPatterName());
            bookKeepingPatternsFX.setPatternType(element.getPatternType());
            bookKeepingPatternsFX.setPatternComment(element.getPatternComment());
            bookKeepingPatternsFXES.add(bookKeepingPatternsFX);
        });
        comboBox.getItems().addAll(bookKeepingPatternsFXES);
        comboBox.setCellFactory(cell -> new ListCell<BookKeepingPatternsFX>() {
            final GridPane gridPane = new GridPane();
            final Label labelId = new Label();
            final Label labelOpis = new Label();
            final Label labelData = new Label();

            {
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(20, 20, 10), new ColumnConstraints(100, 100, 100));
                gridPane.add(labelId, 0, 1);
                gridPane.add(labelOpis, 1, 1);
            }

            @Override
            protected void updateItem(BookKeepingPatternsFX importModelFX, boolean b) {
                super.updateItem(importModelFX, b);

                if (!b && importModelFX != null) {
                    labelId.setText(importModelFX.getId() + ".");
                    labelOpis.setText(importModelFX.getPatternName());
                    setGraphic(gridPane);
                } else {
                    setGraphic(null);
                }
            }
        });
        comboBox.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
            if (newValue != null) {
                try {
                    payListPatternFX.setBookKeepingPatternType(((BookKeepingPatternsFX) newValue).getId());
                    payListPatternFX.setBookKeepingPatterntTypeName(((BookKeepingPatternsFX) newValue).getPatternName());

                    importDAO.updatePayListPattern(payListPatternFX);
                } catch (SQLException e) {
                    DialogUtils.errorDialog(e.getMessage());
                }
            }
        });
        BookKeepingPatterns bookKeepingPatterns1 = null;
        try {
            bookKeepingPatterns1 = importDAO.getBookKeepingPatternsById(payListPatternFX.getBookKeepingPatternType());
            if (bookKeepingPatterns1 == null) {
            } else {
                BookKeepingPatternsFX bookKeepingPatternsFX = new BookKeepingPatternsFX();
                bookKeepingPatternsFX.setId(bookKeepingPatterns1.getId());
                bookKeepingPatternsFX.setPatternTypeName(bookKeepingPatterns1.getPatternTypeName());
                bookKeepingPatternsFX.setPatternName(bookKeepingPatterns1.getPatterName());
                bookKeepingPatternsFX.setPatternComment(bookKeepingPatterns1.getPatternComment());
                bookKeepingPatternsFX.setPatternType(bookKeepingPatterns1.getPatternType());

                comboBox.getSelectionModel().select(bookKeepingPatternsFX);
            }
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void loadData() {
        try {
            ImportModelFX selectedImportModelFX = (ImportModelFX) importComboBox.getSelectionModel().getSelectedItem();
            if (selectedImportModelFX != null) {
                Integer importId = selectedImportModelFX.getId();
                importDAO.generateBookKeepingPatternList(importId);
                importDAO.updateImportListBookKeppingStatus(importId);
                loadDataToTable(importId);
            } else {
                DialogUtils.errorDialog("Wybierz pozycję importu!");
            }
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void loadDataToTable(Integer importId) {
        updatePayMentPatternObservableList(importId);
        listTable.setItems(payListPatternFXES);
        listTableImportId.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, Integer>("importId"));
        listTableListId.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, Integer>("idList"));
        listTableListName.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("numberList"));
        listTableDescriptionList.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("descriptionList"));
        listTableListDateColumn.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("dateList"));
        listTableCodeList.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("departmentCode"));
        listTableAgree.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, String>("agreeList"));
        listTableComboBox.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, ComboBox>("bookKeepingPatternsComboBox"));
        listTableBookButton.setCellValueFactory(new PropertyValueFactory<PayListPatternFX, Button>("bookButton"));


        listTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));

        for (PayListPatternFX item : payListPatternFXES) {
            Button button = item.getBookButton();
            button.setText("Księguj");

            button.setOnAction(e -> {

                try {
                    Integer bookPattern = importDAO.getBookKeepingPatternsById(item.getBookKeepingPatternType()).getPatternType();
               //     System.out.println("WZORZEC " +bookPattern);
                    List<BookKeepingPatternsPosition> bookKeepingPatternsPositions = importDAO.getBookKeepingPatternsPositionsById(item.getBookKeepingPatternType());

                    for(int i=0,j=0;i<bookKeepingPatternsPositions.size();i++){
                        BookKeepingPatternsPosition bookKeepingPatternsPosition=bookKeepingPatternsPositions.get(i);


                        try {

                            Double partSum = 0d;
                            if (bookKeepingPatternsPosition.getPayment()) {
                                partSum = Math.abs(importDAO.getPaymentSum(item.getImportId(), item.getIdList()));
                            }

                            List<Parts> parts = importDAO.getPartsById(item.getBookKeepingPatternType(), bookKeepingPatternsPosition.getPositionId());
                            PodatkiSkladki podatkiSkladki = importDAO.getTaxSUMListById(item.getImportId(), item.getIdList());


                            for (Parts parts1 : parts) {


                                if (parts1.getPartsId() > 0 && parts1.getPartsId() < 16) {

                                    if (parts1.getPartsId() == 1) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getPodatekZaliczkaUS() : partSum - podatkiSkladki.getPodatekZaliczkaUS();
                                    }
                                    if (parts1.getPartsId() == 2) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getEmerytalnaPracownik() : partSum - podatkiSkladki.getEmerytalnaPracownik();
                                    }
                                    if (parts1.getPartsId() == 3) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getRentowaPracownik() : partSum - podatkiSkladki.getRentowaPracownik();
                                    }
                                    if (parts1.getPartsId() == 4) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getChorobowaPracownik() : partSum - podatkiSkladki.getChorobowaPracownik();
                                    }
                                    if (parts1.getPartsId() == 5) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getWypadkowaPracownik() : partSum - podatkiSkladki.getWypadkowaPracownik();
                                    }
                                    if (parts1.getPartsId() == 6) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getEmerytalnaFirma() : partSum - podatkiSkladki.getEmerytalnaFirma();
                                    }
                                    if (parts1.getPartsId() == 7) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getRentowaFirma() : partSum - podatkiSkladki.getRentowaFirma();
                                    }
                                    if (parts1.getPartsId() == 8) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getChorobowaFirma() : partSum - podatkiSkladki.getChorobowaFirma();
                                    }
                                    if (parts1.getPartsId() == 9) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getWypadkowaFirma() : partSum - podatkiSkladki.getWypadkowaFirma();
                                    }
                                    if (parts1.getPartsId() == 10) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getZdrowotnaPracownik() : partSum - podatkiSkladki.getZdrowotnaPracownik();
                                    }
                                    if (parts1.getPartsId() == 11) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getFP() : partSum - podatkiSkladki.getFP();
                                    }
                                    if (parts1.getPartsId() == 12) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getFGSP() : partSum - podatkiSkladki.getFGSP();
                                    }
                                    if (parts1.getPartsId() == 13) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getFEP() : partSum - podatkiSkladki.getFEP();
                                    }
                                    if (parts1.getPartsId() == 14) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getPPKPracownik() : partSum - podatkiSkladki.getPPKPracownik();
                                    }
                                    if (parts1.getPartsId() == 15) {
                                        partSum = parts1.getSymbol().equals("+") ? partSum + podatkiSkladki.getPPKFirma() : partSum - podatkiSkladki.getPPKFirma();
                                    }

                                } else if (parts1.getSymbol().equals("+")) {
                                    partSum = partSum + importDAO.getpartSum(item.getImportId(), item.getIdList(), parts1.getPartsName());
                                } else {
                                    partSum = partSum - importDAO.getpartSum(item.getImportId(), item.getIdList(), parts1.getPartsName());
                                }
                            }
                            partSum = (double) Math.round(partSum * 100) / 100;
                            if(partSum>0) {
                                PK pk = new PK();
                                pk.setPair_number(i + 1);
                                pk.setUnderPair_number(j + 1);
                                pk.setDescription(bookKeepingPatternsPosition.getName());
                                pk.setBlame_account(bookKeepingPatternsPosition.getAccountBlame());
                                pk.setBlame_value(partSum);
                                pk.setHac_account(bookKeepingPatternsPosition.getAccountHas());
                                pk.setHas_value(partSum);
                                postgreSQLDAO.insertPK(pk);
                                System.out.println(pk);
                                if(bookKeepingPatternsPosition.getDistributor()==true){
                                 if(bookPattern==1){
                                     System.out.println("wzorzec ksiegowania 1");
                                 }
                                 if(bookPattern==2){
                                    List<String> departmentList = new ArrayList<String>();
                                     System.out.println("wzorzec ksiegowania 2");
                                 }
                                }
                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                    };

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            });
            initializeComboBox(item);
        }

    }


    private void updatePayMentPatternObservableList(Integer importId) {
        List<PaylistPattern> paylistPatterns = null;
        try {
            payListPatternFXES.clear();
            paylistPatterns = importDAO.getBookKeepingPayListPattern(importId);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        paylistPatterns.forEach(element -> {
            PayListPatternFX payListPatternFX = new PayListPatternFX();
            payListPatternFX.setImportId(element.getImportId());
            payListPatternFX.setIdList(element.getIdList());
            payListPatternFX.setNumberList(element.getNumberList());
            payListPatternFX.setDescriptionList(element.getDescriptionList());
            payListPatternFX.setDateList(element.getDateList().toString());
            payListPatternFX.setDatePayment(element.getDatePayment().toString());
            payListPatternFX.setDateFrom(element.getDateFrom().toString());
            payListPatternFX.setDateTo(element.getDateTo().toString());
            payListPatternFX.setDepartmentCode(element.getDepartmentCode());
            payListPatternFX.setAgreeList(element.getAgreeList());
            payListPatternFX.setBookKeepingPatternType(element.getBookKeepingPatternType());
            payListPatternFX.setBookKeepingPatterntTypeName(element.getBookKeepingPatterntTypeName());
            payListPatternFX.setBook(element.getBook());
            payListPatternFXES.add(payListPatternFX);
        });
    }
}
