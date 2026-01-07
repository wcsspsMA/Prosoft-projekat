/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.time.LocalDate;

/**
 *
 * @author Korisnik
 */
public class Racun {
    
    private Long id;
    private double ukupanIznos;
    private LocalDate datumKupovine;
    private double popust;
    private Staza staza;
    private Zaposleni radnik;
    private Osoba kupac; 

    public Racun() {
    }

    public Racun(double ukupanIznos, LocalDate datumKupovine, double popust, Staza staza, Zaposleni z,Osoba k) {
        this.ukupanIznos = ukupanIznos;
        this.datumKupovine = datumKupovine;
        this.popust = popust;
        this.staza = staza;
        this.radnik = z;
        this.kupac = k;
    }
    
    
    public Racun(double ukupanIznos, LocalDate datumKupovine, double popust, Staza staza) {
        this.ukupanIznos = ukupanIznos;
        this.datumKupovine = datumKupovine;
        this.popust = popust;
        this.staza = staza;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public LocalDate getDatumKupovine() {
        return datumKupovine;
    }

    public void setDatumKupovine(LocalDate datumKupovine) {
        this.datumKupovine = datumKupovine;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public Staza getStaza() {
        return staza;
    }

    public void setStaza(Staza staza) {
        this.staza = staza;
    }

    public Zaposleni getRadnik() {
        return radnik;
    }

    public void setRadnik(Zaposleni radnik) {
        this.radnik = radnik;
    }

    public Osoba getKupac() {
        return kupac;
    }

    public void setKupac(Osoba kupac) {
        this.kupac = kupac;
    }
    
    
    
    
}
