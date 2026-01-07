/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.component;

import domain.Sektor;
import domain.StavkaRacuna;
import domain.TipKarte;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class StavkaRacunaTableModel extends AbstractTableModel{
    private final List<StavkaRacuna> stavkeRacuna;
    private String[] columnNames = {"quantity", "price", "amount", "ticketType","sector"};
    private Class[] columnClass = {Integer.class, Double.class, Double.class, TipKarte.class, Sektor.class};

    public StavkaRacunaTableModel(List<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    @Override
    public int getRowCount() {
        if(stavkeRacuna==null) return 0;
        return stavkeRacuna.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna stavka = stavkeRacuna.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return stavka.getKolicina();
            case 1:
                return stavka.getCena();
            case 2:
                return stavka.getIznos();
            case 3:
                return stavka.getVrstaKarte();
            case 4:
                return stavka.getSektor();
            default:
                return "n/a";
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaRacuna stavka = stavkeRacuna.get(rowIndex);

        switch (columnIndex) {
            case 0:
                stavka.setKolicina((int)aValue);
                break;
            case 1:
                stavka.setCena((double)aValue);
                break;
            case 2:
                stavka.setIznos((double)aValue);
                break;
            case 3:
                stavka.setVrstaKarte((TipKarte)aValue);
                break;
            case 4:
                stavka.setSektor(Sektor.valueOf(aValue.toString()));
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
    
    public void addStavka(StavkaRacuna sr) {
        stavkeRacuna.add(sr);
        int lastIndex = stavkeRacuna.size()-1;
        fireTableRowsInserted(lastIndex, lastIndex);
    }
    
    
}
