/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author Korisnik
 */
public class Osoba {
    
    private Long id;
    private String ime;
    private String prezime;
    private int telefon;
    private KategorijaOsobe kategorija;

    public Osoba() {
    }

    public Osoba(String ime, String prezime, int telefon) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public KategorijaOsobe getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaOsobe kategorija) {
        this.kategorija = kategorija;
    }
    
    

    @Override
    public String toString() {
        return id + ". " + ime + " " + prezime;
    }
    
    
    
    
    
}
