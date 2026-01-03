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
public class ZaSS {
    
    private Zaposleni radnik;
    private StrSprema strucnaSprema;
    private LocalDate datumDobijanja;

    public ZaSS() {
    }

    public ZaSS(Zaposleni radnik, StrSprema strucnaSprema, LocalDate datumDobijanja) {
        this.radnik = radnik;
        this.strucnaSprema = strucnaSprema;
        this.datumDobijanja = datumDobijanja;
    }

    public Zaposleni getRadnik() {
        return radnik;
    }

    public void setRadnik(Zaposleni radnik) {
        this.radnik = radnik;
    }

    public StrSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public LocalDate getDatumDobijanja() {
        return datumDobijanja;
    }

    public void setDatumDobijanja(LocalDate datumDobijanja) {
        this.datumDobijanja = datumDobijanja;
    }
    
    
}
