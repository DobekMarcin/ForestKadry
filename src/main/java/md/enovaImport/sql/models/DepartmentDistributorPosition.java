package md.enovaImport.sql.models;

public class DepartmentDistributorPosition {

    private Integer korg_id;
    private Integer id;
    private String account;

    public DepartmentDistributorPosition() {
    }

    public Integer getKorg_id() {
        return korg_id;
    }

    public void setKorg_id(Integer korg_id) {
        this.korg_id = korg_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}