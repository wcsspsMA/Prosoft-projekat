/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Osoba;
import domain.Racun;
import domain.TipKarte;
import domain.Zaposleni;
import repository.DatabaseBroker;
import java.lang.Exception;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class Controller {
    
    private DatabaseBroker dbbr;

    public Controller() {
        dbbr = new DatabaseBroker();
    }

    
    public Zaposleni vratiUlogovanog(Zaposleni z) throws Exception{
        try{
            dbbr.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        try{
            Zaposleni zaposleni = dbbr.getEmployeeByPassword(z);
            dbbr.commit();
            return zaposleni;
        }
        catch(Exception ex){
            dbbr.rollback();
            ex.printStackTrace();
            throw ex;
        }
        finally{
            dbbr.disconnect();
        }
    }
    
    public List<Racun> vratiRacunePoZaposlenom(Zaposleni z) throws Exception {
        try{
            dbbr.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        try{
            List<Racun> racuni = dbbr.getTicketsByEmployee(z);
            dbbr.commit();
            return racuni;
        }
        catch(Exception ex){
            dbbr.rollback();
            ex.printStackTrace();
            throw ex;
        }
        finally{
            dbbr.disconnect();
        }
    }
    
    public List<Osoba> vratiSveOsobe() throws Exception{
        try{
            dbbr.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        try{
            List<Osoba> osobe = dbbr.getAllPersons();
            dbbr.commit();
            return osobe;
        }
        catch(Exception ex){
            dbbr.rollback();
            ex.printStackTrace();
            throw ex;
        }
        finally{
            dbbr.disconnect();
        }
    }
    
    public double vratiCenuKartaTrajanje(String name, String length) throws Exception {
        try{
            dbbr.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        try{
            double cena = dbbr.getPriceByNameLength(name, length);
            dbbr.commit();
            return cena;
        }
        catch(Exception ex){
            dbbr.rollback();
            ex.printStackTrace();
            throw ex;
        }
        finally{
            dbbr.disconnect();
        }
    }
    
    public TipKarte vratiTipKarte(String name, String len, double price) throws Exception {
        try{
            dbbr.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        try{
            TipKarte tk = dbbr.getTicketType(name, len, price);
            dbbr.commit();
            return tk;
        }
        catch(Exception ex){
            dbbr.rollback();
            ex.printStackTrace();
            throw ex;
        }
        finally{
            dbbr.disconnect();
        }
    }
    
    public Racun instancirajRacun(Racun r) throws Exception{
        try{
            dbbr.connect();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        try{
            Racun racun = dbbr.createBill(r);
            dbbr.commit();
            return racun;
        }
        catch(Exception ex){
            dbbr.rollback();
            ex.printStackTrace();
            throw ex;
        }
        finally{
            dbbr.disconnect();
        }
    }
}
