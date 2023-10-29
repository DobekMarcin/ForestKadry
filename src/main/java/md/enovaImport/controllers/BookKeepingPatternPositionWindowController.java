package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.BookKeepingPatternsFX;
import md.enovaImport.modelsFX.BookKeepingPatternsPositionFX;
import md.enovaImport.modelsFX.ImportModelFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.BookKeepingPatternsPosition;
import md.enovaImport.sql.models.Parts;
import md.enovaImport.utils.DialogUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BookKeepingPatternPositionWindowController {

    private final ObservableList<BookKeepingPatternsPositionFX> bookKeepingPatternsPositionsFX = FXCollections.observableArrayList();
    @FXML
    private TableColumn partsTableColumn;
    @FXML
    private TableView patternPositionTable;
    @FXML
    private TableColumn lpTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableColumn accountBlameTableColumn;
    @FXML
    private TableColumn accountHasTableColumn;
    @FXML
    private TableColumn distributorTableColumn;
    @FXML
    private TableColumn distributorAccountTableColumn;
    private List<BookKeepingPatternsPosition> bookKeepingPatternsPositionList;
    private static final String BOOKKEEPING_PATTERN_ADD_POSITION = "/FXML/BookKeepingPatternAddPositionWindow.fxml";
    private static final String BOOKKEEPING_PATTERN_EDIT_POSITION="/FXML/BookKeepingPatternEditPositionWindow.fxml";
    private static final String BOOKKEEPING_PARTS="/FXML/BookKeepingPartsWindow.fxml";
    private Stage stage;
    private BookKeepingPatternsFX bookKeepingPatternsFX;

    private ImportDAO importDAO = new ImportDAO();

    public void initialize() {
        if(bookKeepingPatternsFX!=null)
        try {
            bookKeepingPatternsPositionList = importDAO.getBookKeepingPatternsPositionsById(bookKeepingPatternsFX.getId());

            bookKeepingPatternsPositionList.forEach(e->{
                BookKeepingPatternsPositionFX bookKeepingPatternsPosition = new BookKeepingPatternsPositionFX();

                bookKeepingPatternsPosition.setPatternId(e.getPatternId());
                bookKeepingPatternsPosition.setPositionId(e.getPositionId());
                bookKeepingPatternsPosition.setName(e.getName());
                bookKeepingPatternsPosition.setDistributor(e.getDistributor());
                bookKeepingPatternsPosition.setAccountHas(e.getAccountHas());
                bookKeepingPatternsPosition.setAccountBlame(e.getAccountBlame());
                bookKeepingPatternsPosition.setAccountDistributor(e.getAccountDisributor());
                bookKeepingPatternsPosition.setPayment(e.getPayment());
                bookKeepingPatternsPosition.setDistributorPosition(e.getDistributorPosition());

                bookKeepingPatternsPosition.getPartsButton().setText("Składniki");
                bookKeepingPatternsPosition.getPartsButton().setOnAction(f->addActionToPartsButton(e));

                bookKeepingPatternsPositionsFX.add(bookKeepingPatternsPosition);
            });

            lpTableColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsPositionFX,Integer>("positionId"));
            nameTableColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsPositionFX,Integer>("name"));
            accountBlameTableColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsPositionFX,String>("accountBlame"));
            accountHasTableColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsPositionFX,String>("accountHas"));
            distributorAccountTableColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsPositionFX,String>("accountDistributor"));

            distributorTableColumn.setCellValueFactory(new PropertyValueFactory<ImportModelFX,Boolean>("distributor"));
            distributorTableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(distributorTableColumn));
            partsTableColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsPositionFX, Button>("partsButton"));

            patternPositionTable.setItems(bookKeepingPatternsPositionsFX);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addActionToPartsButton(BookKeepingPatternsPosition e){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_PARTS));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));

        try {
            Parent parent = fxmlLoader.load();
            BookKeepingPartsWindowController bookKeepingPartsWindowController = fxmlLoader.getController();
            bookKeepingPartsWindowController.setPatternId(bookKeepingPatternsFX.getId());
            bookKeepingPartsWindowController.setPositionId(e.getPositionId());
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Wybierz składnik");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            bookKeepingPartsWindowController.initialize();
            stage.showAndWait();
            bookKeepingPatternsPositionsFX.clear();
            initialize();
        } catch (IOException f) {

            DialogUtils.errorDialog("Błąd aplikacji!");
        }

    }

    public void addPositionButton() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_PATTERN_ADD_POSITION));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Parent parent = fxmlLoader.load();
            BookKeepingPatternAddPositionWindowController bookKeepingPatternAddPositionWindowController = fxmlLoader.getController();


            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Dodaj nową pozycję");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            bookKeepingPatternAddPositionWindowController.setBookKeepingPatternsFX(bookKeepingPatternsFX);
            bookKeepingPatternAddPositionWindowController.setStage(stage);
            stage.showAndWait();
            bookKeepingPatternsPositionsFX.clear();
            initialize();
        } catch (IOException exception) {
            DialogUtils.errorDialog("Błąd aplikacji!");
        }
    }

    public BookKeepingPatternsFX getBookKeepingPatternsFX() {
        return bookKeepingPatternsFX;
    }

    public void setBookKeepingPatternsFX(BookKeepingPatternsFX bookKeepingPatternsFX) {
        this.bookKeepingPatternsFX = bookKeepingPatternsFX;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void deletePositionButton() {
        BookKeepingPatternsPositionFX bookKeepingPatternsPositionFX= (BookKeepingPatternsPositionFX) patternPositionTable.getSelectionModel().getSelectedItem();
        if(bookKeepingPatternsPositionFX==null){
            DialogUtils.errorDialog("Zaznacz rekord!");
        }else{
        try {
            List<Parts> partsList=importDAO.getPartsById(bookKeepingPatternsPositionFX.getPatternId(),bookKeepingPatternsPositionFX.getPositionId());
            if(partsList.size()>0){
                DialogUtils.informationDialog("Usuń składniki wzorca!");
            }else {
                importDAO.deleteBookKeepingPatternPositionById(bookKeepingPatternsPositionFX.getPatternId(), bookKeepingPatternsPositionFX.getPositionId());
                bookKeepingPatternsPositionsFX.clear();
                initialize();
            }
            } catch (SQLException e) {
            DialogUtils.errorDialog("Problem z połączeniem z bazą danych!");
        }}
    }

    public void editPositionButton() {
        if(patternPositionTable.getSelectionModel().getSelectedItem()==null){
            DialogUtils.informationDialog("Zaznacz pozycję!");
        }else{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_PATTERN_EDIT_POSITION));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Parent parent = fxmlLoader.load();
            BookKeepingPatternEditPositionWindowController bookKeepingPatternEditPositionWindowController = fxmlLoader.getController();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Edytuj pozycję!");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            bookKeepingPatternEditPositionWindowController.setBookKeepingPatternsPositionFX((BookKeepingPatternsPositionFX) patternPositionTable.getSelectionModel().getSelectedItem());
            bookKeepingPatternEditPositionWindowController.setStage(stage);
            bookKeepingPatternEditPositionWindowController.initialize();
            stage.showAndWait();
            bookKeepingPatternsPositionsFX.clear();
            initialize();
        } catch (IOException exception) {
            DialogUtils.errorDialog("Błąd aplikacji!");
            exception.printStackTrace();
        }}
    }
}
