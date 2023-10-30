package md.enovaImport.sql.models;

public class PK {

    private Integer user_id;
    private Integer pair_number;
    private Integer underPair_number;
    private String blame_account;
    private Double blame_value;
    private String hac_account;
    private Double has_value;
    private String description;


    @Override
    public String toString() {
        return "PK{" +
                ", description='" + description + '\'' +
                ", blame_account='" + blame_account + '\'' +
                ", blame_value=" + blame_value +
                ", hac_account='" + hac_account + '\'' +
                ", has_value=" + has_value +

                '}';
    }

    public PK() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPair_number() {
        return pair_number;
    }

    public void setPair_number(Integer pair_number) {
        this.pair_number = pair_number;
    }

    public Integer getUnderPair_number() {
        return underPair_number;
    }

    public void setUnderPair_number(Integer underPair_number) {
        this.underPair_number = underPair_number;
    }

    public String getBlame_account() {
        return blame_account;
    }

    public void setBlame_account(String blame_account) {
        this.blame_account = blame_account;
    }

    public Double getBlame_value() {
        return blame_value;
    }

    public void setBlame_value(Double blame_value) {
        this.blame_value = blame_value;
    }

    public String getHac_account() {
        return hac_account;
    }

    public void setHac_account(String hac_account) {
        this.hac_account = hac_account;
    }

    public Double getHas_value() {
        return has_value;
    }

    public void setHas_value(Double has_value) {
        this.has_value = has_value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
