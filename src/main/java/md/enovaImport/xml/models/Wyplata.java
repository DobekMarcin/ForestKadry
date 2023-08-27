package md.enovaImport.xml.models;

import java.util.ArrayList;
import java.util.List;

public class Wyplata {
    private Integer id;
    private Integer numerWyplaty;
    private Integer kodPracownika;
    private String kodWydzialuKosztowego;
    private Double doWyplaty;
    private String nazwiskoImie;
    private String pesel;
    private Double kwotaStawki;

    List<ElementWyplaty> elementWyplatyList = new ArrayList<>();

    public Wyplata() {
    }

    public Wyplata(Integer id,Integer numerWyplaty, Integer kodPracownika, String kodWydzialuKosztowego, Double doWyplaty,String nazwiskoImie,String pesel,Double kwotaStawki, List<ElementWyplaty> elementWyplatyList) {
        this.id=id;
        this.numerWyplaty = numerWyplaty;
        this.kodPracownika = kodPracownika;
        this.kodWydzialuKosztowego = kodWydzialuKosztowego;
        this.doWyplaty = doWyplaty;
        this.nazwiskoImie=nazwiskoImie;
        this.pesel=pesel;
        this.kwotaStawki=kwotaStawki;
        this.elementWyplatyList = elementWyplatyList;
    }

    public Integer getNumerWyplaty() {
        return numerWyplaty;
    }

    public void setNumerWyplaty(Integer numerWyplaty) {
        this.numerWyplaty = numerWyplaty;
    }

    public Integer getKodPracownika() {
        return kodPracownika;
    }

    public void setKodPracownika(Integer kodPracownika) {
        this.kodPracownika = kodPracownika;
    }

    public String getKodWydzialuKosztowego() {
        return kodWydzialuKosztowego;
    }

    public void setKodWydzialuKosztowego(String kodWydzialuKosztowego) {
        this.kodWydzialuKosztowego = kodWydzialuKosztowego;
    }

    public List<ElementWyplaty> getElementWyplatyList() {
        return elementWyplatyList;
    }

    public void setElementWyplatyList(List<ElementWyplaty> elementWyplatyList) {
        this.elementWyplatyList = elementWyplatyList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDoWyplaty() {
        return doWyplaty;
    }

    public void setDoWyplaty(Double doWyplaty) {
        this.doWyplaty = doWyplaty;
    }

    public String getNazwiskoImie() {
        return nazwiskoImie;
    }

    public void setNazwiskoImie(String nazwiskoImie) {
        this.nazwiskoImie = nazwiskoImie;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Double getKwotaStawki() {
        return kwotaStawki;
    }

    public void setKwotaStawki(Double kwotaStawki) {
        this.kwotaStawki = kwotaStawki;
    }

    @Override
    public String toString() {
        return "Wyplata{" +
                "id=" + id +
                ", numerWyplaty=" + numerWyplaty +
                ", kodPracownika=" + kodPracownika +
                ", kodWydzialuKosztowego='" + kodWydzialuKosztowego + '\'' +
                ", doWyplaty=" + doWyplaty +
                ", nazwiskoImie='" + nazwiskoImie + '\'' +
                ", pesel='" + pesel + '\'' +
                ", kwotaStawki=" + kwotaStawki +
                ", elementWyplatyList=" + elementWyplatyList +
                '}';
    }
}
