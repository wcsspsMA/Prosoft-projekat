/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Korisnik
 */
public class StavkaRacuna {
    
    private Racun racun;
    private Long rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Sektor sektor;
    private TipKarte vrstaKarte;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun,int kolicina, double cena, double iznos, Sektor sektor, TipKarte vrstaKarte) {
        this.racun = racun;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.sektor = sektor;
        this.vrstaKarte = vrstaKarte;
    }

    public Long getRb() {
        return rb;
    }

    public void setRb(Long rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Sektor getSektor() {
        return sektor;
    }

    public void setSektor(Sektor sektor) {
        this.sektor = sektor;
    }

    public TipKarte getVrstaKarte() {
        return vrstaKarte;
    }

    public void setVrstaKarte(TipKarte vrstaKarte) {
        this.vrstaKarte = vrstaKarte;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    
    
    
    
}
