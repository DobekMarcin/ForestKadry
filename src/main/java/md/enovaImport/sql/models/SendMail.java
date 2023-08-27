package md.enovaImport.sql.models;

import java.sql.Date;


public class SendMail {

    private Integer importId;
    private Integer listId;
    private String listName;
    private Integer code;
    private String name;
    private String pathFile;
    private Boolean isFile;
    private Boolean isSend;
    private Integer amountId;
    private Double amount;
    private Date paymentDate;
    private Date Listdate;
    private Double agreementAmount;

    public Double getAgreementAmount() {
        return agreementAmount;
    }

    public void setAgreementAmount(Double agreementAmount) {
        this.agreementAmount = agreementAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getListdate() {
        return Listdate;
    }

    public void setListdate(Date listdate) {
        Listdate = listdate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAmountId() {
        return amountId;
    }

    public void setAmountId(Integer amountId) {
        this.amountId = amountId;
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public Boolean getFile() {
        return isFile;
    }

    public void setFile(Boolean file) {
        isFile = file;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean send) {
        isSend = send;
    }
}
