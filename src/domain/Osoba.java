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
    //treba li mi ovde atribut za kategoriju osobe

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

    @Override
    public String toString() {
        return "Osoba: " + ime + " " + prezime;
    }
    
    
    
    
    
}
