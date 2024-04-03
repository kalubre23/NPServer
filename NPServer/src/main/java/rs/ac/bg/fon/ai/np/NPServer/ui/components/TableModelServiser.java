/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.ui.components;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;

/**
 *
 * @author Asus
 */
public class TableModelServiser extends AbstractTableModel{
    List<Serviser> ulogovaniServiseri;
    String[] naziviKolona = {"Ime", "Prezime"};

    public TableModelServiser() {
        ulogovaniServiseri = new ArrayList<>();
    }
    
    
    @Override
    public int getRowCount() {
        return ulogovaniServiseri.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Serviser s = ulogovaniServiseri.get(rowIndex);
        
        switch(columnIndex){
            case 0: return s.getIme();
            case 1: return s.getPrezime();
            default: return "n\\a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return naziviKolona[column];
    }
    
    public void dodajServisera(Serviser s){
        ulogovaniServiseri.add(s);
        fireTableDataChanged();
    }

    public void obrisiServisera(Serviser ulogovaniServiser) {
        ulogovaniServiseri.remove(ulogovaniServiser);
        fireTableDataChanged();
    }
    
}
