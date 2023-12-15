package md.enovaImport.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.ImportModel;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;
import md.enovaImport.xml.models.ElementWyplaty;
import md.enovaImport.xml.models.ListaPlac;
import md.enovaImport.xml.models.PodatkiSkladki;
import md.enovaImport.xml.models.Wyplata;
import md.enovaImport.xml.sax.ReadXmlSaxParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewImportWindowController {

    @FXML
    private TextField importDate;
    @FXML
    private Button fileButton;
    @FXML
    private Label importFileStatus;
    @FXML
    private TextField importName;
    @FXML
    private Button importButton;

    private File file;
    private SimpleStringProperty importNameProperty = new SimpleStringProperty();
    private SimpleStringProperty labelImportFileproperty = new SimpleStringProperty();
    private ImportDAO importDAO;

    private ReadXmlSaxParser readXmlSaxParser;
    public static final String BORDER_PANE_MAIN_FXML = "/FXML/ImportWindow.fxml";

    private  MainWindowController mainWindowController;

    public MainWindowController getMainWindowController() {
        return mainWindowController;
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
    public void initialize() {

        importDAO = new ImportDAO();
        importNameProperty.bindBidirectional(importName.textProperty());
        fileButton.disableProperty().bind(importNameProperty.isEmpty());

        importNameProperty.setValue("");
        labelImportFileproperty.bindBidirectional(importFileStatus.textProperty());
        labelImportFileproperty.setValue("");
        importButton.disableProperty().bind(labelImportFileproperty.isEmpty().or(importNameProperty.isEmpty()));
        file = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String strDate = formatter.format(new Date());

        importDate.setText(strDate);
        importDate.setEditable(false);

        importName.setFocusTraversable(true);
    }

    public void importFileButton() {
        Stage stage = (Stage) importButton.getScene().getWindow();

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(extensionFilter);
        fileChooser.setTitle(FXMLUtils.getBundle("import.file"));
        file = fileChooser.showOpenDialog(stage);
        if (file != null){
            System.out.println(file.getName());
        labelImportFileproperty.setValue( file.getName());}
        else
            labelImportFileproperty.setValue("");
    }

    public void imporXML() {

        Boolean checkname=false;
        try {
            checkname=importDAO.checkImportName(importNameProperty.getValue());
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
if(checkname){
    DialogUtils.informationDialog("Import o tej nazwie już istnieje! Zmień nazwę i zaimportuj ponownie!");
}else {
    readXmlSaxParser = new ReadXmlSaxParser();
    try {

        List<ListaPlac> listaPlac = readXmlSaxParser.getImportData(file.toURI().toURL().toExternalForm());
        ImportModel importModel = new ImportModel();
        importModel.setOpis(importNameProperty.getValue());
        importModel.setDataImportu(new Date());
        Integer importId = importDAO.getImportId();
        importDAO.addNewImport(importModel, importId);

        for (int i = 0; listaPlac.size() > i; i++) {
            ListaPlac listaPlacTemp = listaPlac.get(i);
            importDAO.addListaPlac(listaPlacTemp, i + 1, importId);
            for (int x = 0; listaPlacTemp.getListaWyplat().size() > x; x++) {
                Wyplata wyplataTemp = listaPlacTemp.getListaWyplat().get(x);
                importDAO.addWyplata(wyplataTemp, i + 1, x + 1, importId);
                for (int y = 0; wyplataTemp.getElementWyplatyList().size() > y; y++) {
                    ElementWyplaty elementWyplatyTemp = wyplataTemp.getElementWyplatyList().get(y);
                    importDAO.addElementWyplaty(elementWyplatyTemp, x + 1, y + 1, i + 1, importId);
                    for (int z = 0; elementWyplatyTemp.getPodatkiSkladkiList().size() > z; z++) {
                        PodatkiSkladki podatkiSkladkiTemp = elementWyplatyTemp.getPodatkiSkladkiList().get(z);
                        importDAO.addPodatkiSkladki(podatkiSkladkiTemp, i + 1, x + 1, y + 1, z + 1, importId);
                    }
                }
            }
        }

        DialogUtils.importConfirmation();
        initialize();
        mainWindowController.setImportWindow();

    } catch (Exception e) {
        DialogUtils.errorDialog(e.getMessage());
    }
}
    }

    public void backButton() {
        mainWindowController.setImportWindow();
    }
}
