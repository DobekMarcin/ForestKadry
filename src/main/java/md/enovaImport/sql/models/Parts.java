package md.enovaImport.sql.models;

public class Parts {

    private Integer patternId;
    private Integer positionId;
    private Integer id;
    private Integer partsId;
    private String partsName;
    private String symbol;

    public Parts() {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartsId() {
        return partsId;
    }

    public void setPartsId(Integer partsId) {
        this.partsId = partsId;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
