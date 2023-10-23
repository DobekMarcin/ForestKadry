package md.enovaImport.sql.models;

public class BookKeepingPatternsPosition {

    private Integer patternId;
    private Integer positionId;
    private String name;
    private String accountBlame;
    private String accountHas;
    private Boolean distributor;
    private String accountDisributor;

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
}
