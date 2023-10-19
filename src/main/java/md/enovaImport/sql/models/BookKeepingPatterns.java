package md.enovaImport.sql.models;

public class BookKeepingPatterns {

    private Integer id;
    private String patterName;
    private Integer patternType;
    private String patternTypeName;
    private String patternComment;

    @Override
    public String toString() {
        return "BookKeepingPatterns{" +
                "id=" + id +
                ", patterName='" + patterName + '\'' +
                ", patternType=" + patternType +
                ", patternTypeName='" + patternTypeName + '\'' +
                ", patternComment='" + patternComment + '\'' +
                '}';
    }

    public BookKeepingPatterns(Integer id, String patterName, Integer patternType, String patternTypeName, String patternComment) {
        this.id = id;
        this.patterName = patterName;
        this.patternType = patternType;
        this.patternTypeName = patternTypeName;
        this.patternComment = patternComment;
    }

    public BookKeepingPatterns() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatterName() {
        return patterName;
    }

    public void setPatterName(String patterName) {
        this.patterName = patterName;
    }

    public Integer getPatternType() {
        return patternType;
    }

    public void setPatternType(Integer patternType) {
        this.patternType = patternType;
    }

    public String getPatternTypeName() {
        return patternTypeName;
    }

    public void setPatternTypeName(String patternTypeName) {
        this.patternTypeName = patternTypeName;
    }

    public String getPatternComment() {
        return patternComment;
    }

    public void setPatternComment(String patternComment) {
        this.patternComment = patternComment;
    }
}
