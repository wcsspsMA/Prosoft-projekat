/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import domain.Osoba;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Staza;
import domain.Zaposleni;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korisnik
 */
public class DatabaseBroker {
    
    private Connection connection;
    
    public void connect() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost/prosoft_projekat";
            String user = "root";
            String password = "mihailo";
            
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Uspesno povezivanje sa bazom!");
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void commit() throws SQLException {
        try{
            connection.commit();
            System.out.println("Uspesno izvrsen commit!");
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void rollback() throws SQLException {
        try{
            connection.rollback();
            System.out.println("Uspesno izvrsen rollback!");
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void disconnect() throws SQLException {
        try{
            if(connection!=null && !connection.isClosed()){
                connection.close();
                System.out.println("Uspesno raskinuta veza sa bazom!");
            }
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public Zaposleni getEmployeeByPassword(Zaposleni z) throws SQLException{
        try{
            String query = "SELECT * FROM zaposleni WHERE username=? AND password=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, z.getKorisnickoIme());
            ps.setString(2, z.getSifra());
            ResultSet rs = ps.executeQuery();
            Zaposleni z2 = new Zaposleni();
            if(rs.next()){
                z2.setId((Long)rs.getLong("id"));
                z2.setIme(rs.getString("firstname"));
                z2.setPrezime(rs.getString("lastname"));
                z2.setKorisnickoIme(rs.getString("username"));
                z2.setSifra(rs.getString("password"));
                z2.setTelefon(rs.getInt("phone"));
             
            }
            else{
                throw new SQLException("Ne postoji korisnik u bazi sa tim kredencijalima!");
            }
            rs.close();
            ps.close();
            System.out.println("Uspesno logovanje!");
            return z2;
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
        
    }
    
    public List<Racun> getTicketsByEmployee(Zaposleni z) throws SQLException{
        try{
            String query = "SELECT id, totalAmount, purchaseDate, discount, track FROM racun WHERE employee=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, z.getId());
            ResultSet rs = ps.executeQuery();
            List<Racun> racuni = new ArrayList<>();
            
            while(rs.next()){
                double ukupanIznos = rs.getDouble("totalAmount");
                double popust = rs.getDouble("discount");
                LocalDate datum = rs.getDate("purchaseDate").toLocalDate();
                Staza staza = Staza.valueOf(rs.getString("track"));
                Long id = rs.getLong("id");
                Racun r = new Racun(ukupanIznos, datum, popust, staza);
                r.setId(id);
                racuni.add(r);
            }
            rs.close();
            ps.close();
            return racuni;
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }

    
    
    
    
}
