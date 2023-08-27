package md.enovaImport.modelsFX;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PodatkiSkladkiFX {

    private SimpleIntegerProperty idPodatku = new SimpleIntegerProperty();
    private SimpleDoubleProperty podatekZaliczkaUS = new SimpleDoubleProperty();
    private SimpleDoubleProperty emerytalnaPracownik = new SimpleDoubleProperty();
    private SimpleDoubleProperty rentowaPracownik = new SimpleDoubleProperty();
    private SimpleDoubleProperty chorobowaPracownik = new SimpleDoubleProperty();
    private SimpleDoubleProperty wypadkowaPracownik = new SimpleDoubleProperty();
    private SimpleDoubleProperty emerytalnaFirma = new SimpleDoubleProperty();
    private SimpleDoubleProperty rentowaFirma = new SimpleDoubleProperty();
    private SimpleDoubleProperty chorobowaFirma = new SimpleDoubleProperty();
    private SimpleDoubleProperty wypadkowaFirma = new SimpleDoubleProperty();
    private SimpleDoubleProperty zdrowotnaPracownik = new SimpleDoubleProperty();
    private SimpleDoubleProperty FP = new SimpleDoubleProperty();
    private SimpleDoubleProperty FGSP = new SimpleDoubleProperty();
    private SimpleDoubleProperty FEP = new SimpleDoubleProperty();
    private SimpleDoubleProperty PPKPracownik = new SimpleDoubleProperty();
    private SimpleDoubleProperty PPKFirma = new SimpleDoubleProperty();

    public int getIdPodatku() {
        return idPodatku.get();
    }

    public SimpleIntegerProperty idPodatkuProperty() {
        return idPodatku;
    }

    public void setIdPodatku(int idPodatku) {
        this.idPodatku.set(idPodatku);
    }

    public double getPodatekZaliczkaUS() {
        return podatekZaliczkaUS.get();
    }

    public SimpleDoubleProperty podatekZaliczkaUSProperty() {
        return podatekZaliczkaUS;
    }

    public void setPodatekZaliczkaUS(double podatekZaliczkaUS) {
        this.podatekZaliczkaUS.set(podatekZaliczkaUS);
    }

    public double getEmerytalnaPracownik() {
        return emerytalnaPracownik.get();
    }

    public SimpleDoubleProperty emerytalnaPracownikProperty() {
        return emerytalnaPracownik;
    }

    public void setEmerytalnaPracownik(double emerytalnaPracownik) {
        this.emerytalnaPracownik.set(emerytalnaPracownik);
    }

    public double getRentowaPracownik() {
        return rentowaPracownik.get();
    }

    public SimpleDoubleProperty rentowaPracownikProperty() {
        return rentowaPracownik;
    }

    public void setRentowaPracownik(double rentowaPracownik) {
        this.rentowaPracownik.set(rentowaPracownik);
    }

    public double getChorobowaPracownik() {
        return chorobowaPracownik.get();
    }

    public SimpleDoubleProperty chorobowaPracownikProperty() {
        return chorobowaPracownik;
    }

    public void setChorobowaPracownik(double chorobowaPracownik) {
        this.chorobowaPracownik.set(chorobowaPracownik);
    }

    public double getWypadkowaPracownik() {
        return wypadkowaPracownik.get();
    }

    public SimpleDoubleProperty wypadkowaPracownikProperty() {
        return wypadkowaPracownik;
    }

    public void setWypadkowaPracownik(double wypadkowaPracownik) {
        this.wypadkowaPracownik.set(wypadkowaPracownik);
    }

    public double getEmerytalnaFirma() {
        return emerytalnaFirma.get();
    }

    public SimpleDoubleProperty emerytalnaFirmaProperty() {
        return emerytalnaFirma;
    }

    public void setEmerytalnaFirma(double emerytalnaFirma) {
        this.emerytalnaFirma.set(emerytalnaFirma);
    }

    public double getRentowaFirma() {
        return rentowaFirma.get();
    }

    public SimpleDoubleProperty rentowaFirmaProperty() {
        return rentowaFirma;
    }

    public void setRentowaFirma(double rentowaFirma) {
        this.rentowaFirma.set(rentowaFirma);
    }

    public double getChorobowaFirma() {
        return chorobowaFirma.get();
    }

    public SimpleDoubleProperty chorobowaFirmaProperty() {
        return chorobowaFirma;
    }

    public void setChorobowaFirma(double chorobowaFirma) {
        this.chorobowaFirma.set(chorobowaFirma);
    }

    public double getWypadkowaFirma() {
        return wypadkowaFirma.get();
    }

    public SimpleDoubleProperty wypadkowaFirmaProperty() {
        return wypadkowaFirma;
    }

    public void setWypadkowaFirma(double wypadkowaFirma) {
        this.wypadkowaFirma.set(wypadkowaFirma);
    }

    public double getZdrowotnaPracownik() {
        return zdrowotnaPracownik.get();
    }

    public SimpleDoubleProperty zdrowotnaPracownikProperty() {
        return zdrowotnaPracownik;
    }

    public void setZdrowotnaPracownik(double zdrowotnaPracownik) {
        this.zdrowotnaPracownik.set(zdrowotnaPracownik);
    }

    public double getFP() {
        return FP.get();
    }

    public SimpleDoubleProperty FPProperty() {
        return FP;
    }

    public void setFP(double FP) {
        this.FP.set(FP);
    }

    public double getFGSP() {
        return FGSP.get();
    }

    public SimpleDoubleProperty FGSPProperty() {
        return FGSP;
    }

    public void setFGSP(double FGSP) {
        this.FGSP.set(FGSP);
    }

    public double getFEP() {
        return FEP.get();
    }

    public SimpleDoubleProperty FEPProperty() {
        return FEP;
    }

    public void setFEP(double FEP) {
        this.FEP.set(FEP);
    }

    public double getPPKPracownik() {
        return PPKPracownik.get();
    }

    public SimpleDoubleProperty PPKPracownikProperty() {
        return PPKPracownik;
    }

    public void setPPKPracownik(double PPKPracownik) {
        this.PPKPracownik.set(PPKPracownik);
    }

    public double getPPKFirma() {
        return PPKFirma.get();
    }

    public SimpleDoubleProperty PPKFirmaProperty() {
        return PPKFirma;
    }

    public void setPPKFirma(double PPKFirma) {
        this.PPKFirma.set(PPKFirma);
    }
}
