package md.enovaImport.sql.models;

import java.util.Date;

public class ImportModel {

    private Integer id;
    private String opis;
    private Date dataImportu;
    private Boolean email;

    public ImportModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDataImportu() {
        return dataImportu;
    }

    public void setDataImportu(Date dataImportu) {
        this.dataImportu = dataImportu;
    }

    @Override
    public String toString() {
        return "ImportModel{" +
                "id=" + id +
                ", opis='" + opis + '\'' +
                ", dataImportu=" + dataImportu +
                ", email=" + email +
                '}';
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }
}
