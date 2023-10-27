package md.enovaImport.sql.models;

import javafx.scene.control.Button;

public class BookKeepingPatternsPosition {

    private Integer patternId;
    private Integer positionId;
    private String name;
    private String accountBlame;
    private String accountHas;
    private Boolean distributor;
    private String accountDisributor;
    private Boolean payment;




    public BookKeepingPatternsPosition() {
    }

    public Integer getPatternId() {
        return patternId;
    }

    public void setPatternId(Integer patternId) {
        this.patternId = patternId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountBlame() {
        return accountBlame;
    }

    public void setAccountBlame(String accountBlame) {
        this.accountBlame = accountBlame;
    }

    public String getAccountHas() {
        return accountHas;
    }

    public void setAccountHas(String accountHas) {
        this.accountHas = accountHas;
    }

    public Boolean getDistributor() {
        return distributor;
    }

    public void setDistributor(Boolean distributor) {
        this.distributor = distributor;
    }

    public String getAccountDisributor() {
        return accountDisributor;
    }

    public void setAccountDisributor(String accountDisributor) {
        this.accountDisributor = accountDisributor;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "BookKeepingPatternsPosition{" +
                "patternId=" + patternId +
                ", positionId=" + positionId +
                ", name='" + name + '\'' +
                ", accountBlame='" + accountBlame + '\'' +
                ", accountHas='" + accountHas + '\'' +
                ", distributor=" + distributor +
                ", accountDisributor='" + accountDisributor + '\'' +
                ", payment=" + payment +
                '}';
    }
}
