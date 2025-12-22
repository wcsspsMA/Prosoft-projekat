/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import domain.Zaposleni;
import repository.DatabaseBroker;
import java.lang.Exception;

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
}
