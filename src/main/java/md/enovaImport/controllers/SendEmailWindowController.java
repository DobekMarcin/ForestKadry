package md.enovaImport.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import md.enovaImport.email.EmailUtil;
import md.enovaImport.modelsFX.ImportModelFX;
import md.enovaImport.modelsFX.SendMailFX;
import md.enovaImport.pdf.PdfCreator;
import md.enovaImport.sql.jdbc.ImportDAO;
import md.enovaImport.sql.models.ImportModel;
import md.enovaImport.sql.models.SendMail;
import md.enovaImport.utils.DialogUtils;
import md.enovaImport.utils.FXMLUtils;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.File;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static javax.mail.Session.getInstance;


public class SendEmailWindowController {

    private final ObservableList<ImportModelFX> importModelFXES = FXCollections.observableArrayList();
    private final ImportDAO importDAO = new ImportDAO();
    private final ObservableList<SendMailFX> sendMailFXObservableList = FXCollections.observableArrayList();
    private final String EMAIL = "place@forest.gorlice.pl";
    private final String EMAIL_PASSWORD = "4S+$M22a!'$pjnU";
    @FXML
    private TableColumn sendEmailTableIdListColumn;
    @FXML
    private TableColumn sendEmailTableNameListColumn;
    @FXML
    private TableView sendEmailTable;
    @FXML
    private TableColumn sendEmailTableIdColumn;
    @FXML
    private TableColumn sendEmailTableCodeColumn;
    @FXML
    private TableColumn sendEmailTableNameColumn;
    @FXML
    private TableColumn sendEmailTablePathColumn;
    @FXML
    private TableColumn sendEmailTableGenerateColumn;
    @FXML
    private TableColumn sendEmailTableViewColumn;
    @FXML
    private TableColumn sendEmailTableEmailColumn;
    @FXML
    private TableColumn sendEmailTableDeleteColumn;
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
                gridPane.getColumnConstraints().addAll(new ColumnConstraints(10, 30, 10), new ColumnConstraints(100, 100, 100));
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

    public void test() {


    }


    private void updatePersonDicObservableList(Integer importId) {
        List<SendMail> sendMailsList = null;
        try {
            sendMailFXObservableList.clear();
            sendMailsList = importDAO.getSendMailList(importId);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        sendMailsList.forEach(element -> {
            SendMailFX sendMailFX = new SendMailFX();
            sendMailFX.setImportId(element.getImportId());
            sendMailFX.setListId(element.getListId());
            sendMailFX.setCode(element.getCode());
            sendMailFX.setName(element.getName());
            sendMailFX.setListName(element.getListName());
            sendMailFX.setPathFile(element.getPathFile());
            sendMailFX.setIsFile(element.getFile());
            sendMailFX.setIsSend(element.getSend());
            sendMailFX.setAmountId(element.getAmountId());
            sendMailFX.setAmount(element.getAmount());
            sendMailFX.setPaymentDate(element.getPaymentDate().toString());
            sendMailFX.setListDate(element.getListdate().toString());
            sendMailFX.setAgreementAmount(element.getAgreementAmount());
            sendMailFXObservableList.add(sendMailFX);
        });
    }

    public void loadDataButton() {
        try {
            ImportModelFX selectedImportModelFX = (ImportModelFX) importComboBox.getSelectionModel().getSelectedItem();
            Boolean checkFlag = null;
            if (selectedImportModelFX != null) {
                Integer importId = selectedImportModelFX.getId();
                importDAO.generateEmailList(importId);
                importDAO.updateImportListEmailStatus(importId);
                createFolder(importId);
                loadDataToTable(importId);
            } else {
                DialogUtils.errorDialog("Wybierz pozycję importu!");
            }
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    private void loadDataToTable(Integer importId) {
        updatePersonDicObservableList(importId);

        sendEmailTable.setItems(sendMailFXObservableList);
        sendEmailTableIdColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Integer>("importId"));
        sendEmailTableIdListColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Integer>("listId"));
        sendEmailTableNameListColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, String>("listName"));
        sendEmailTableCodeColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Integer>("code"));
        sendEmailTableNameColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, String>("name"));
        sendEmailTablePathColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, String>("pathFile"));
        sendEmailTableGenerateColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("generateButton"));
        sendEmailTableViewColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("viewButton"));
        sendEmailTableEmailColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("emailButton"));
        sendEmailTableDeleteColumn.setCellValueFactory(new PropertyValueFactory<SendMailFX, Button>("deleteButton"));

        sendEmailTable.setPlaceholder(new Label(FXMLUtils.getBundle("empty.table")));
        addListenerToButtons();
    }

    private void addListenerToButtons() {
        for (SendMailFX item : sendMailFXObservableList) {
            Button generateButton = item.getGenerateButton();
            Button viewButton = item.getViewButton();
            Button emeilButton = item.getEmailButton();
            Button deleteButton = item.getDeleteButton();
            generateButton.setText("Generuj");
            viewButton.setText("Podgląd");
            emeilButton.setText("Email");
            deleteButton.setText("Usuń");

            generateButton.setOnAction((actionEvent) -> {
                PdfCreator pdfCreator = new PdfCreator();
                pdfCreator.createPdf(item);
            });

            viewButton.setOnAction((actionEvent) -> {
                if ((new File(item.getPathFile())).exists()) {
                    Process p = null;
                    try {
                        p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + item.getPathFile());
                        p.waitFor();
                    } catch (InterruptedException | IOException e) {
                        DialogUtils.errorDialog(e.getMessage());
                    }
                } else {
                    DialogUtils.informationDialog("Plik nie istnieje!");
                }
            });
            emeilButton.setOnAction((actionEvent) -> {
                sendEmail(item);
            });
        }
    }

    private void createFolder(Integer importId) {
        String rootPath = "C:\\EMAIL\\";
        File rootFile = new File(rootPath);
        if (!rootFile.exists()) {
            try {
                Path directory = Files.createDirectory(Paths.get("C:\\EMAIL\\"));
            } catch (IOException e) {

                DialogUtils.errorDialog(e.getMessage());
            }
        }
        String importName = null;
        try {
            importName = importDAO.getImportName(importId);
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        Path path = Paths.get("C:\\EMAIL\\" + importName + "\\");
        File importFile = new File("C:\\EMAIL\\" + importName + "\\");
        if (!importFile.exists()) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                DialogUtils.errorDialog(e.getMessage());
            }
        }
    }

    private void sendEmail(SendMailFX sendMailFX) {

        String content = "W załączniku znajduje się wydruk listy płac. " +
                "\nProszę nie odpowiadać na tę wiadomość. " +
                "\nEwentualne uwagi prosimy zgłaszać w formie pisemnej do działu Kadr i Płac." +
                "\n\n--------------------------" +
                "Forest Gorlice sp. z o.o.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "serwer1473971.home.pl");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        String mailTo = "";
        try {
            mailTo = importDAO.getEmail(sendMailFX.getCode());
        } catch (SQLException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, EMAIL_PASSWORD);
            }
        };
        Session session = getInstance(props, auth);
        try {
            EmailUtil.sendAttachmentEmail(session, mailTo, "Lista płac: " + sendMailFX.getName(), content, sendMailFX);
            DialogUtils.informationDialog("Wiadomość wysłana poprawnie!");
        } catch (MessagingException e) {
            DialogUtils.errorDialog(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }
}