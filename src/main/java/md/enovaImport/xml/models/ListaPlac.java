package md.enovaImport.xml.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaPlac {

    private Integer idListy;
    private String numerListy;
    private String opisListy;
    private Date dataListy;
    private Date dataWyplaty;
    private Date okresListyOd;
    private Date okresListyDo;
    private String KodWydzialu;
    private Boolean zatwierdzona;
    private List<Wyplata> listaWyplat = new ArrayList();

    public ListaPlac() {
    }

    public ListaPlac(Integer idListy,String numerListy, String opisListy, Date dataListy, Date dataWyplaty, Date okresListyOd, Date okresListyDo, String kodWydzialu, Boolean zatwierdzona, List<Wyplata> listaWyplat) {
        this.idListy = idListy;
        this.numerListy = numerListy;
        this.opisListy = opisListy;
        this.dataListy = dataListy;
        this.dataWyplaty = dataWyplaty;
        this.okresListyOd = okresListyOd;
        this.okresListyDo = okresListyDo;
        this.KodWydzialu = kodWydzialu;
        this.zatwierdzona = zatwierdzona;
        this.listaWyplat = listaWyplat;
    }

    public Integer getIdListy() {
        return idListy;
    }

    public void setIdListy(Integer idListy) {
        this.idListy = idListy;
    }

    public String getNumerListy() {
        return numerListy;
    }

    public void setNumerListy(String numerListy) {
        this.numerListy = numerListy;
    }

    public String getOpisListy() {
        return opisListy;
    }

    public void setOpisListy(String opisListy) {
        this.opisListy = opisListy;
    }

    public Date getDataListy() {
        return dataListy;
    }

    public void setDataListy(Date dataListy) {
        this.dataListy = dataListy;
    }

    public Date getDataWyplaty() {
        return dataWyplaty;
    }

    public void setDataWyplaty(Date dataWyplaty) {
        this.dataWyplaty = dataWyplaty;
    }

    public Date getOkresListyOd() {
        return okresListyOd;
    }

    public void setOkresListyOd(Date okresListyOd) {
        this.okresListyOd = okresListyOd;
    }

    public Date getOkresListyDo() {
        return okresListyDo;
    }

    public void setOkresListyDo(Date okresListyDo) {
        this.okresListyDo = okresListyDo;
    }

    public String getKodWydzialu() {
        return KodWydzialu;
    }
    public void setKodWydzialu(String kodWydzialu) {
        KodWydzialu = kodWydzialu;
    }

    public Boolean getZatwierdzona() {
        return zatwierdzona;
    }

    public void setZatwierdzona(Boolean zatwierdzona) {
        this.zatwierdzona = zatwierdzona;
    }

    public List<Wyplata> getListaWyplat() {
        return listaWyplat;
    }

    public void setListaWyplat(List<Wyplata> listaWyplat) {
        this.listaWyplat = listaWyplat;
    }

    @Override
    public String toString() {
        return "ListaPlac{" +
                "idListy=" + idListy +
                ", numerListy='" + numerListy + '\'' +
                ", opisListy='" + opisListy + '\'' +
                ", dataListy=" + dataListy +
                ", dataWyplaty=" + dataWyplaty +
                ", okresListyOd=" + okresListyOd +
                ", okresListyDo=" + okresListyDo +
                ", KodWydzialu='" + KodWydzialu + '\'' +
                ", zatwierdzona=" + zatwierdzona +
                ", listaWyplat=" + listaWyplat +
                '}';
    }
}
