package md.enovaImport.xml.models;

public class ElementSlownik {
    private Integer id;
    private String nazwa;
    private String alias;
    private Boolean czyDrukowac;
    private Integer kolejnosc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getCzyDrukowac() {
        return czyDrukowac;
    }

    public void setCzyDrukowac(Boolean czyDrukowac) {
        this.czyDrukowac = czyDrukowac;
    }

    public Integer getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(Integer kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    @Override
    public String toString() {
        return "ElementSlownik{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", alias='" + alias + '\'' +
                ", czyDrukowac=" + czyDrukowac +
                ", kolejnosc=" + kolejnosc +
                '}';
    }
}
