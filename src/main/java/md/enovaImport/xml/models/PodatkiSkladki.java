package md.enovaImport.xml.models;

public class PodatkiSkladki {

    private Integer idPodatku;
    private Double podatekZaliczkaUS;
    private Double emerytalnaPracownik;
    private Double rentowaPracownik;
    private Double chorobowaPracownik;
    private Double wypadkowaPracownik;
    private Double emerytalnaFirma;
    private Double rentowaFirma;
    private Double chorobowaFirma;
    private Double wypadkowaFirma;
    private Double zdrowotnaPracownik;
    private Double FP;
    private Double FGSP;
    private Double FEP;
    private Double PPKPracownik;
    private Double PPKFirma;

    public PodatkiSkladki(Integer idPodatku,Double podatekZaliczkaUS, Double emerytalnaPracownik, Double rentowaPracownik, Double chorobowaPracownik, Double wypadkowaPracownik, Double emerytalnaFirma, Double rentowaFirma, Double chorobowaFirma, Double wypadkowaFirma, Double zdrowotnaPracownik, Double FP, Double FGSP, Double FEP, Double PPKPracownik, Double PPKFirma) {
        this.idPodatku=idPodatku;
        this.podatekZaliczkaUS = podatekZaliczkaUS;
        this.emerytalnaPracownik = emerytalnaPracownik;
        this.rentowaPracownik = rentowaPracownik;
        this.chorobowaPracownik = chorobowaPracownik;
        this.wypadkowaPracownik = wypadkowaPracownik;
        this.emerytalnaFirma = emerytalnaFirma;
        this.rentowaFirma = rentowaFirma;
        this.chorobowaFirma = chorobowaFirma;
        this.wypadkowaFirma = wypadkowaFirma;
        this.zdrowotnaPracownik = zdrowotnaPracownik;
        this.FP = FP;
        this.FGSP = FGSP;
        this.FEP = FEP;
        this.PPKPracownik = PPKPracownik;
        this.PPKFirma = PPKFirma;
    }

    public void setPodatekZaliczkaUS(Double podatekZaliczkaUS) {
        this.podatekZaliczkaUS = podatekZaliczkaUS;
    }

    public void setEmerytalnaPracownik(Double emerytalnaPracownik) {
        this.emerytalnaPracownik = emerytalnaPracownik;
    }

    public void setRentowaPracownik(Double rentowaPracownik) {
        this.rentowaPracownik = rentowaPracownik;
    }

    public void setChorobowaPracownik(Double chorobowaPracownik) {
        this.chorobowaPracownik = chorobowaPracownik;
    }

    public void setWypadkowaPracownik(Double wypadkowaPracownik) {
        this.wypadkowaPracownik = wypadkowaPracownik;
    }

    public void setEmerytalnaFirma(Double emerytalnaFirma) {
        this.emerytalnaFirma = emerytalnaFirma;
    }

    public void setRentowaFirma(Double rentowaFirma) {
        this.rentowaFirma = rentowaFirma;
    }

    public void setChorobowaFirma(Double chorobowaFirma) {
        this.chorobowaFirma = chorobowaFirma;
    }

    public void setWypadkowaFirma(Double wypadkowaFirma) {
        this.wypadkowaFirma = wypadkowaFirma;
    }

    public void setZdrowotnaPracownik(Double zdrowotnaPracownik) {
        this.zdrowotnaPracownik = zdrowotnaPracownik;
    }

    public void setFP(Double FP) {
        this.FP = FP;
    }

    public void setFGSP(Double FGSP) {
        this.FGSP = FGSP;
    }

    public void setFEP(Double FEP) {
        this.FEP = FEP;
    }

    public void setPPKPracownik(Double PPKPracownik) {
        this.PPKPracownik = PPKPracownik;
    }

    public void setPPKFirma(Double PPKFirma) {
        this.PPKFirma = PPKFirma;
    }

    public Double getPodatekZaliczkaUS() {
        return podatekZaliczkaUS;
    }

    public Double getEmerytalnaPracownik() {
        return emerytalnaPracownik;
    }

    public Double getRentowaPracownik() {
        return rentowaPracownik;
    }

    public Double getChorobowaPracownik() {
        return chorobowaPracownik;
    }

    public Double getWypadkowaPracownik() {
        return wypadkowaPracownik;
    }

    public Double getEmerytalnaFirma() {
        return emerytalnaFirma;
    }

    public Double getRentowaFirma() {
        return rentowaFirma;
    }

    public Double getChorobowaFirma() {
        return chorobowaFirma;
    }

    public Double getWypadkowaFirma() {
        return wypadkowaFirma;
    }

    public Double getZdrowotnaPracownik() {
        return zdrowotnaPracownik;
    }

    public Double getFP() {
        return FP;
    }

    public Double getFGSP() {
        return FGSP;
    }

    public Double getFEP() {
        return FEP;
    }

    public Double getPPKPracownik() {
        return PPKPracownik;
    }

    public Double getPPKFirma() {
        return PPKFirma;
    }

    public PodatkiSkladki() {
    }

    public Integer getIdPodatku() {
        return idPodatku;
    }

    public void setIdPodatku(Integer idPodatku) {
        this.idPodatku = idPodatku;
    }

    @Override
    public String toString() {
        return "PodatkiSkladki{" +
                "idPodatku=" + idPodatku +
                ", podatekZaliczkaUS=" + podatekZaliczkaUS +
                ", emerytalnaPracownik=" + emerytalnaPracownik +
                ", rentowaPracownik=" + rentowaPracownik +
                ", chorobowaPracownik=" + chorobowaPracownik +
                ", wypadkowaPracownik=" + wypadkowaPracownik +
                ", emerytalnaFirma=" + emerytalnaFirma +
                ", rentowaFirma=" + rentowaFirma +
                ", chorobowaFirma=" + chorobowaFirma +
                ", wypadkowaFirma=" + wypadkowaFirma +
                ", zdrowotnaPracownik=" + zdrowotnaPracownik +
                ", FP=" + FP +
                ", FGSP=" + FGSP +
                ", FEP=" + FEP +
                ", PPKPracownik=" + PPKPracownik +
                ", PPKFirma=" + PPKFirma +
                '}';
    }
}
