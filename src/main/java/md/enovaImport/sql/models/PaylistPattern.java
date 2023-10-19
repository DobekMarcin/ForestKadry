package md.enovaImport.sql.models;

import java.util.Date;

public class PaylistPattern {

    private Integer importId;
    private Integer idList;
    private String numberList;
    private String descriptionList;
    private Date dateList;
    private Date datePayment;
    private Date dateFrom;
    private Date dateTo;
    private String departmentCode;
    private Boolean agreeList;
    private Integer bookKeepingPatternType;
    private String bookKeepingPatterntTypeName;
private Boolean book;

    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    public Boolean getBook() {
        return book;
    }

    public void setBook(Boolean book) {
        this.book = book;
    }

    public PaylistPattern() {
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public String getNumberList() {
        return numberList;
    }

    public void setNumberList(String numberList) {
        this.numberList = numberList;
    }

    public String getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(String descriptionList) {
        this.descriptionList = descriptionList;
    }

    public Date getDateList() {
        return dateList;
    }

    public void setDateList(Date dateList) {
        this.dateList = dateList;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Boolean getAgreeList() {
        return agreeList;
    }

    public void setAgreeList(Boolean agreeList) {
        this.agreeList = agreeList;
    }

    public Integer getBookKeepingPatternType() {
        return bookKeepingPatternType;
    }

    public void setBookKeepingPatternType(Integer bookKeepingPatternType) {
        this.bookKeepingPatternType = bookKeepingPatternType;
    }

    public String getBookKeepingPatterntTypeName() {
        return bookKeepingPatterntTypeName;
    }

    public void setBookKeepingPatterntTypeName(String bookKeepingPatterntTypeName) {
        this.bookKeepingPatterntTypeName = bookKeepingPatterntTypeName;
    }
}
