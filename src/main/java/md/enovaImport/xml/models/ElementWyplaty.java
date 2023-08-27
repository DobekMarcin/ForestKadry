package md.enovaImport.xml.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ElementWyplaty {
    private Integer idElementu;
    private String nazwaElementu;
    private String kodElementu;
    private Date okresElementuOd;
    private Date okresElementuDo;
    private String czasElementu;
    private Integer dniElementu;
    private Double wartoscElementu;
    private List<PodatkiSkladki> podatkiSkladkiList = new ArrayList();

    public ElementWyplaty() {
    }

    public ElementWyplaty(Integer idElementu,String nazwaElementu, String kodElementu, Date okresElementuOd, Date okresElementuDo, String czasElementu, Integer dniElementu, Double wartoscElementu, List<PodatkiSkladki> podatkiSkladkiList) {
        this.idElementu = idElementu;
        this.nazwaElementu = nazwaElementu;
        this.kodElementu = kodElementu;
        this.okresElementuOd = okresElementuOd;
        this.okresElementuDo = okresElementuDo;
        this.czasElementu = czasElementu;
        this.dniElementu = dniElementu;
        this.wartoscElementu = wartoscElementu;
        this.podatkiSkladkiList = podatkiSkladkiList;
    }

    public Integer getIdElementu() {
        return idElementu;
    }

    public void setIdElementu(Integer idElementu) {
        this.idElementu = idElementu;
    }

    public void setNazwaElementu(String nazwaElementu) {
        this.nazwaElementu = nazwaElementu;
    }

    public void setKodElementu(String kodElementu) {
        this.kodElementu = kodElementu;
    }

    public void setOkresElementuOd(Date okresElementuOd) {
        this.okresElementuOd = okresElementuOd;
    }

    public void setOkresElementuDo(Date okresElementuDo) {
        this.okresElementuDo = okresElementuDo;
    }

    public void setCzasElementu(String czasElementu) {
        this.czasElementu = czasElementu;
    }

    public void setDniElementu(Integer dniElementu) {
        this.dniElementu = dniElementu;
    }

    public void setWartoscElementu(Double wartoscElementu) {
        this.wartoscElementu = wartoscElementu;
    }

    public void setPodatkiSkladkiList(List<PodatkiSkladki> podatkiSkladkiList) {
        this.podatkiSkladkiList = podatkiSkladkiList;
    }

    public String getNazwaElementu() {
        return nazwaElementu;
    }

    public String getKodElementu() {
        return kodElementu;
    }

    public Date getOkresElementuOd() {
        return okresElementuOd;
    }

    public Date getOkresElementuDo() {
        return okresElementuDo;
    }

    public String getCzasElementu() {
        return czasElementu;
    }

    public Integer getDniElementu() {
        return dniElementu;
    }

    public Double getWartoscElementu() {
        return wartoscElementu;
    }

    public List<PodatkiSkladki> getPodatkiSkladkiList() {
        return podatkiSkladkiList;
    }

    @Override
    public String toString() {
        return "ElementWyplaty{" +
                "idElementu=" + idElementu +
                ", nazwaElementu='" + nazwaElementu + '\'' +
                ", kodElementu='" + kodElementu + '\'' +
                ", okresElementuOd=" + okresElementuOd +
                ", okresElementuDo=" + okresElementuDo +
                ", czasElementu='" + czasElementu + '\'' +
                ", dniElementu=" + dniElementu +
                ", wartoscElementu=" + wartoscElementu +
                ", podatkiSkladkiList=" + podatkiSkladkiList +
                '}';
    }
}
