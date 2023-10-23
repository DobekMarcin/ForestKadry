package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import md.enovaImport.modelsFX.BookKeepingPatternsFX;
import md.enovaImport.modelsFX.SimplePersonFX;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.BookKeepingPatterns;
import md.enovaImport.sql.models.SimplePerson;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;

import java.awt.*;
import java.awt.print.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BookkeepingWindowController {


    private static final String BOOKKEEPING_ADD_PATTERN_DIALOG_WINDOW = "/FXML/BookkeepingAddPatterDialogWindow.fxml";
    private static final String BOOKKEEPING_PATTERN_POSITION="/FXML/BookKeepingPatternPositionWindow.fxml";
    private final ImportDAO importDAO = new ImportDAO();
    private final ObservableList<BookKeepingPatternsFX> bookKeepingPatternsFXES = FXCollections.observableArrayList();
    @FXML
    private TableColumn bookKeepingPatternTypeButtonPositionColumn;
    @FXML
    private TableView booKeepingTable;
    @FXML
    private TableColumn bookKeepingIdColumn;
    @FXML
    private TableColumn bookKeepingpatternColumn;
    @FXML
    private TableColumn bookKeepingPatternTypeColumn;
    @FXML
    private TableColumn bookKeepingPatternTypeNameColumn;
    private MainWindowController mainWindowController;
    private List<BookKeepingPatterns> bookKeepingPatterns;

    public void initialize() {
        try {
            bookKeepingPatterns = importDAO.getBookKeepingPatterns();

            bookKeepingPatterns.forEach(element -> {
                BookKeepingPatternsFX bookKeepingPatterns1 = new BookKeepingPatternsFX();
                bookKeepingPatterns1.setId(element.getId());
                bookKeepingPatterns1.setPatternName(element.getPatterName());
                bookKeepingPatterns1.setPatternType(element.getPatternType());
                bookKeepingPatterns1.setPatternTypeName(element.getPatternTypeName());
                bookKeepingPatterns1.setPatternComment(element.getPatternComment());
                bookKeepingPatternsFXES.add(bookKeepingPatterns1);
            });
            bookKeepingIdColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsFX, Integer>("Id"));
            bookKeepingpatternColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsFX, String>("patternName"));
            bookKeepingPatternTypeColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsFX, Integer>("patternType"));
            bookKeepingPatternTypeNameColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsFX, String>("patternTypeName"));
            bookKeepingPatternTypeButtonPositionColumn.setCellValueFactory(new PropertyValueFactory<BookKeepingPatternsFX, Button>("bookKeepingPositionButton"));
            booKeepingTable.setItems(bookKeepingPatternsFXES);

            booKeepingTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));

            bookKeepingPatternsFXES.forEach(e->{
                e.getBookKeepingPositionButton().setText("Pozycje");
                e.getBookKeepingPositionButton().setOnAction(handler->{

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_PATTERN_POSITION));
                    fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
                    try {
                        Parent parent = fxmlLoader.load();
                        BookKeepingPatternPositionWindowController bookKeepingPatternPositionWindowController = fxmlLoader.getController();


                        Scene scene = new Scene(parent);
                        Stage stage = new Stage();
                        stage.setTitle("Pozycje wzorca");
                        stage.setResizable(false);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        bookKeepingPatternPositionWindowController.setStage(stage);
                        bookKeepingPatternPositionWindowController.setBookKeepingPatternsFX(e);
                          stage.showAndWait();
                      //  bookKeepingPatternsFXES.clear();
                      //  initialize();

                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }
                });



            });

        } catch (SQLException e) {
            DialogUtils.errorDialog("Nie można odczytać danych z bazy!");
        }

    }


    public void addPattern() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(BOOKKEEPING_ADD_PATTERN_DIALOG_WINDOW));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.messages"));
        try {
            Parent parent = fxmlLoader.load();
            BookkeepingAddPatterDialogWindowController bookkeepingAddPatterDialogWindowController = fxmlLoader.getController();

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Dodaj nowy wzorzec");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            bookkeepingAddPatterDialogWindowController.setStage(stage);
            stage.showAndWait();
            bookKeepingPatternsFXES.clear();
            initialize();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void deletePattern() {

        BookKeepingPatternsFX bookKeepingPatterns1 = (BookKeepingPatternsFX) booKeepingTable.getSelectionModel().getSelectedItem();
        if (bookKeepingPatterns1 == null) {
            DialogUtils.errorDialog("Nie wybrano rekordu.");
        } else {
            try {
                importDAO.deleteBookKeepingPatternById(bookKeepingPatterns1.getId());
            } catch (SQLException e) {
                DialogUtils.errorDialog("Problem z komunikacją z bazą danych!");
            }
            bookKeepingPatternsFXES.clear();
            initialize();
        }
    }
}
