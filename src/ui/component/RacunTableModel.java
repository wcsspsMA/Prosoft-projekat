/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.component;

import domain.Racun;
import domain.Staza;
import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Korisnik
 */
public class RacunTableModel extends AbstractTableModel{
    
    private final List<Racun> racuni;
    private String[] columnNames = {"id", "totalAmount", "purchaseDate", "discount", "track"};
    private Class[] columnClass = {Long.class, Double.class, LocalDate.class, Double.class, Staza.class};
    

    public RacunTableModel(List<Racun> racuni) {
        this.racuni = racuni;
    }

    @Override
    public int getRowCount() {
        if(racuni==null) return 0;
        return racuni.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun racun = racuni.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return racun.getId();
            case 1:
                return racun.getUkupanIznos();
            case 2:
                return racun.getDatumKupovine();
            case 3:
                return racun.getPopust();
            case 4:
                return racun.getStaza();

            default:
                return "n/a";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Racun racun = racuni.get(rowIndex);

        switch (columnIndex) {
            case 0:
                racun.setId((Long)aValue);
                break;
            case 1:
                racun.setUkupanIznos((double)aValue);
                break;
            case 2:
                racun.setDatumKupovine((LocalDate)aValue);
                break;
            case 3:
                racun.setPopust((double)aValue);
                break;
            case 4:
                racun.setStaza(Staza.valueOf(aValue.toString()));
                break;
        }
    }
    
        @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
    
}
