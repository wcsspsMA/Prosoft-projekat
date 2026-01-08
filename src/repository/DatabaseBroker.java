/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import domain.KategorijaOsobe;
import domain.Osoba;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Staza;
import domain.TipKarte;
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

    
    
    public List<Osoba> getAllPersons() throws SQLException {
        try{
            String query = "SELECT o.id, o.firstName, o.lastName, o.phone,k.id, k.title, k.description  FROM osoba o INNER JOIN kategorijaosobe k ON(o.category=k.id)";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            List<Osoba> osobe = new ArrayList<>();
            while(rs.next()){
                Osoba o = new Osoba();
                o.setId(rs.getLong("o.id"));
                o.setIme(rs.getString("o.firstName"));
                o.setPrezime(rs.getString("o.lastName"));
                o.setTelefon(rs.getInt("o.phone"));
                KategorijaOsobe cat = new KategorijaOsobe();
                cat.setId(rs.getLong("k.id"));
                cat.setNaziv(rs.getString("k.title"));
                cat.setOpis(rs.getString("k.description"));
                o.setKategorija(cat);
                osobe.add(o);
            }
            rs.close();
            statement.close();
            return osobe;
            
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public double getPriceByNameLength(String name, String length) throws SQLException {
        try{
            String query = "SELECT price FROM tipkarte WHERE name=? AND length=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,name);
            ps.setString(2,length);
            ResultSet rs = ps.executeQuery();
            double cena=0;
            if(rs.next()){
                cena=rs.getDouble(1);
            }
            rs.close();
            ps.close();
            return cena;
            
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public TipKarte getTicketType(String name, String length, double price) throws SQLException {
        try{
            String query = "SELECT * FROM tipkarte WHERE name=? AND length=? AND price=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, length);
            ps.setDouble(3, price);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Long id = rs.getLong("id");
                String title = rs.getString("name");
                String len = rs.getString("length");
                String des = rs.getString("description");
                double cost = rs.getDouble("price");
                TipKarte tk = new TipKarte(title, len, des, cost);
                tk.setId(id);
                return tk;
            }
            rs.close();
            ps.close();
            return null;
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public Racun createBill(Racun r) throws SQLException {
        try{
            String query = "INSERT INTO racun(totalAmount,purchaseDate,discount,track,employee,person) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, r.getUkupanIznos());
            ps.setDate(2, Date.valueOf(r.getDatumKupovine()));
            ps.setDouble(3, r.getPopust());
            ps.setString(4, r.getStaza().toString());
            ps.setLong(5,r.getRadnik().getId());
            ps.setLong(6,r.getKupac().getId());
            int  res = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                r.setId(rs.getLong(1));
            }
            rs.close();
            ps.close();
            return r;
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public KategorijaOsobe getPersonsCategory(String title) throws SQLException{
        try{
            String query = "SELECT * from kategorijaosobe WHERE title=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Long id = rs.getLong("id");
                String naziv = rs.getString("title");
                String des = rs.getString("description");
                KategorijaOsobe cat = new KategorijaOsobe(naziv, des);
                cat.setId(id);
                return cat;
            }
            else{
                throw new SQLException("Ne postoji ta kategorija u bazi!");
            }
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void createBillPart(StavkaRacuna sr) throws SQLException {
        try{
            String query = "INSERT INTO stavkaracuna(idRacun,rb,quantity,price,amount,ticketType,sector) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, sr.getRacun().getId());
            ps.setLong(2, sr.getRb());
            ps.setInt(3, sr.getKolicina());
            ps.setDouble(4, sr.getCena());
            ps.setDouble(5, sr.getIznos());
            ps.setLong(6,sr.getVrstaKarte().getId());
            ps.setString(7,sr.getSektor().toString());
            int res = ps.executeUpdate();
            if(res==1){
                System.out.println("Uspesno kreirana stavka!");
            }
            else{
                throw new SQLException("Neuspesno unosenje stavke!");
            }
            
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public Osoba createPerson (Osoba o) throws SQLException {
        try{
            String query = "INSERT INTO osoba(firstname,lastName,phone,category) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,o.getIme());
            ps.setString(2,o.getPrezime());
            ps.setInt(3, o.getTelefon());
            ps.setLong(4, o.getKategorija().getId());
            int res = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                o.setId(rs.getLong(1));
            }
            else{
                throw new SQLException("Neuspesno kreiranje osobe!");
            }
            rs.close();
            ps.close();

            return o;
            
        }
        catch(SQLException ex){
            System.out.println("Doslo je do greske!");
            ex.printStackTrace();
            throw ex;
        }
    }
    
}
