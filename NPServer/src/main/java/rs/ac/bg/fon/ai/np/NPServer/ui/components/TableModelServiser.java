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
 * Predstavlja model tabele za klasu Serviser. Nasledjuje apstraktni model tabele i imeplementira njegove metode.
 * 
 * Ima listu svih uloganih servisera koji ce se prikazati u tabeli kao i metode za dodavanje 
 * i brisanje servisera iz ove liste.
 * 
 * @see AbstractTableModel
 * @author Luka Obrenic
 */
public class TableModelServiser extends AbstractTableModel{
	/**
	 * Lista svih ulogovanih servisera kao lista tipa Serviser.
	 * @see Serviser
	 */
    List<Serviser> ulogovaniServiseri;
    /**
     * Nazivi kolona tabele kao niz stringova.
     */
    String[] naziviKolona = {"Ime", "Prezime"};

    /**
     * Neparametrizovani konstruktor koji inicijalizuje listu ulogovanih servisera,
     */
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
    
    /**
     * Dodaje servisera u listu ulogovanih servisera i osvezava prikaz tabele na grafickoj formi.
     * 
     * @param s serviser koji se ulogovao
     */
    public void dodajServisera(Serviser s){
        ulogovaniServiseri.add(s);
        fireTableDataChanged();
    }

    /**
     * Brise servisera iz liste ulogovanih servisera i osvezava prikaz tabele na grafickoj formi.
     * 
     * @param ulogovaniServiser koji se izlogovao
     */
    public void obrisiServisera(Serviser ulogovaniServiser) {
        ulogovaniServiseri.remove(ulogovaniServiser);
        fireTableDataChanged();
    }
    
}
