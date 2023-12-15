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
import md.enovaImport.xml.models.Wyplata;

import javax.mail.Part;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.sun.javafx.scene.layout.ScaledMath.round;

public class ListPatternWindowController {

    private final ObservableList<PayListPatternFX> payListPatternFXES = FXCollections.observableArrayList();
    private final ObservableList<ImportModelFX> importModelFXES = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    private final PostgreSQLDAO postgreSQLDAO = new PostgreSQLDAO();
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
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(30, 30, 30), new ColumnConstraints(200, 200, 100));
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

                    for (int i = 0, j = 0; i < bookKeepingPatternsPositions.size(); i++, j++) {
                        BookKeepingPatternsPosition bookKeepingPatternsPosition = bookKeepingPatternsPositions.get(i);


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
                            if (partSum > 0) {
                                PK pk = new PK();
                                pk.setPair_number(j + 1);
                                pk.setUnderPair_number(1);
                                pk.setDescription(bookKeepingPatternsPosition.getName());
                                pk.setBlame_account(bookKeepingPatternsPosition.getAccountBlame());
                                pk.setBlame_value(partSum);
                                pk.setHac_account(bookKeepingPatternsPosition.getAccountHas());
                                pk.setHas_value(partSum);
                                postgreSQLDAO.insertPK(pk);
                                System.out.println("### " + pk + "###");

                                if (bookKeepingPatternsPosition.getDistributor()) {

                                    if (bookPattern == 1) {
                                        int k = 0;
                                        j = j + 1;
                                        List<Wyplata> wyplataList = importDAO.getWyplataList(item.getImportId(), item.getIdList());

                                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                        Date date = format.parse(item.getDateFrom());

                                        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                        int month = localDate.getMonthValue();
                                        int year = localDate.getYear();

                                        List<OrderWork> orderWorksList = postgreSQLDAO.getOrderWork(month, year, wyplataList);

                                        Double timeSum = 0d;

                                        for (OrderWork itemList : orderWorksList) {
                                            timeSum += itemList.getTime();
                                        }
                                        Double percentSum = 0d;
                                        Double accountSum = 0d;
                                        List<PK> pkList= new ArrayList<>();

                                        for (OrderWork itemList : orderWorksList) {
                                            PK workersPk = new PK();
                                            String distributorAccount = importDAO.getDepartmentDistrubotorAccountPosition("FIRMA", bookKeepingPatternsPosition.getDistributorPosition());
                                            workersPk.setBlame_account(distributorAccount);
                                            Double percent = (itemList.getTime() / timeSum);
                                            percentSum = percentSum + percent;
                                            workersPk.setBlame_value(Math.round((percent * pk.getBlame_value()) * 100d) / 100d);
                                            if(workersPk.getBlame_value()>0)
                                            pkList.add(workersPk);

                                        }
                                        pkList.sort(new Comparator<PK>() {
                                            @Override
                                            public int compare(PK o1, PK o2) {
                                                return Double.compare(o1.getBlame_value(),o2.getBlame_value());
                                            }
                                        });

                                        Double blameSUM=0d;
                                        for(PK pkk :pkList){
                                            blameSUM+=Math.round(pkk.getBlame_value()*100d)/100d;
                                        }
                                        blameSUM=Math.round(blameSUM*100d)/100d;
                                        System.out.println(blameSUM);
                                        Double diffrence = Math.round((pk.getBlame_value()-blameSUM)*100d)/100d;
                                        System.out.println(diffrence);
                                        pkList.get(pkList.size()-1).setBlame_value(pkList.get(pkList.size()-1).getBlame_value()+diffrence);

                                        Double blameSUM2=0d;
                                        for(PK pkk :pkList){
                                            blameSUM2+=Math.round(pkk.getBlame_value()*100d)/100d;
                                        }
                                        System.out.println(blameSUM2);

                                        for(PK pkk :pkList){
                                            System.out.println(pkk);
                                        }
                                    }

                                    if (bookPattern == 2) {
                                        int k = 0;
                                        j = j + 1;
                                        List<String> departmentList;
                                        departmentList = importDAO.getDepartmentListByList(item.getImportId(), item.getIdList());

                                        for (String departmentItem : departmentList) {
                                            Double distributorPartSum = 0d;
                                            if (bookKeepingPatternsPosition.getPayment()) {
                                                distributorPartSum = Math.abs(importDAO.getPaymentSumByDepartement(item.getImportId(), item.getIdList(), departmentItem));
                                            }

                                            List<Parts> partsDistributor = importDAO.getPartsById(item.getBookKeepingPatternType(), bookKeepingPatternsPosition.getPositionId());
                                            PodatkiSkladki podatkiSkladkiDistributor = importDAO.getTaxSUMListByIdAndByDepartment(item.getImportId(), item.getIdList(), departmentItem);

                                            for (Parts parts1 : partsDistributor) {


                                                if (parts1.getPartsId() > 0 && parts1.getPartsId() < 16) {

                                                    if (parts1.getPartsId() == 1) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getPodatekZaliczkaUS() : distributorPartSum - podatkiSkladkiDistributor.getPodatekZaliczkaUS();
                                                    }
                                                    if (parts1.getPartsId() == 2) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getEmerytalnaPracownik() : distributorPartSum - podatkiSkladkiDistributor.getEmerytalnaPracownik();
                                                    }
                                                    if (parts1.getPartsId() == 3) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getRentowaPracownik() : distributorPartSum - podatkiSkladkiDistributor.getRentowaPracownik();
                                                    }
                                                    if (parts1.getPartsId() == 4) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getChorobowaPracownik() : distributorPartSum - podatkiSkladkiDistributor.getChorobowaPracownik();
                                                    }
                                                    if (parts1.getPartsId() == 5) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getWypadkowaPracownik() : distributorPartSum - podatkiSkladkiDistributor.getWypadkowaPracownik();
                                                    }
                                                    if (parts1.getPartsId() == 6) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getEmerytalnaFirma() : distributorPartSum - podatkiSkladkiDistributor.getEmerytalnaFirma();
                                                    }
                                                    if (parts1.getPartsId() == 7) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getRentowaFirma() : distributorPartSum - podatkiSkladkiDistributor.getRentowaFirma();
                                                    }
                                                    if (parts1.getPartsId() == 8) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getChorobowaFirma() : distributorPartSum - podatkiSkladkiDistributor.getChorobowaFirma();
                                                    }
                                                    if (parts1.getPartsId() == 9) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getWypadkowaFirma() : distributorPartSum - podatkiSkladkiDistributor.getWypadkowaFirma();
                                                    }
                                                    if (parts1.getPartsId() == 10) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getZdrowotnaPracownik() : distributorPartSum - podatkiSkladkiDistributor.getZdrowotnaPracownik();
                                                    }
                                                    if (parts1.getPartsId() == 11) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getFP() : distributorPartSum - podatkiSkladkiDistributor.getFP();
                                                    }
                                                    if (parts1.getPartsId() == 12) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getFGSP() : distributorPartSum - podatkiSkladkiDistributor.getFGSP();
                                                    }
                                                    if (parts1.getPartsId() == 13) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getFEP() : distributorPartSum - podatkiSkladkiDistributor.getFEP();
                                                    }
                                                    if (parts1.getPartsId() == 14) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getPPKPracownik() : distributorPartSum - podatkiSkladkiDistributor.getPPKPracownik();
                                                    }
                                                    if (parts1.getPartsId() == 15) {
                                                        distributorPartSum = parts1.getSymbol().equals("+") ? distributorPartSum + podatkiSkladkiDistributor.getPPKFirma() : distributorPartSum - podatkiSkladkiDistributor.getPPKFirma();
                                                    }

                                                } else if (parts1.getSymbol().equals("+")) {
                                                    {
                                                        distributorPartSum = distributorPartSum + importDAO.getpartSumByDepartment(item.getImportId(), item.getIdList(), parts1.getPartsName(), departmentItem);
                                                    }
                                                } else {
                                                    distributorPartSum = distributorPartSum - importDAO.getpartSumByDepartment(item.getImportId(), item.getIdList(), parts1.getPartsName(), departmentItem);
                                                }
                                            }
                                            if (departmentItem.isEmpty()) {
                                                departmentItem = item.getDepartmentCode();
                                            }
                                            distributorPartSum = (double) Math.round(distributorPartSum * 100d) / 100d;
                                            System.out.println(departmentItem + " " + distributorPartSum);
                                            String distributorAccount = importDAO.getDepartmentDistrubotorAccountPosition(departmentItem, bookKeepingPatternsPosition.getDistributorPosition());
                                            if (distributorPartSum > 0) {
                                                PK underPk = new PK();
                                                underPk.setPair_number(j + 1);
                                                underPk.setUnderPair_number(++k);
                                                underPk.setHas_value(k == 1 ? partSum : 0);
                                                underPk.setHac_account(k == 1 ? bookKeepingPatternsPosition.getAccountDisributor() : "");
                                                underPk.setDescription("Płace: rozksiegowanie");
                                                underPk.setBlame_value(distributorPartSum);
                                                underPk.setBlame_account(distributorAccount);
                                                postgreSQLDAO.insertPK(underPk);

                                                System.out.println("Under PK: " + underPk);
                                            }
                                        }

                                    }
                                    if (bookPattern == 3) {
                                        System.out.println("wzorzec ksiegowania 1");
                                    }


                                }
                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }

                    }

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
