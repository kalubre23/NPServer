/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.ui.components;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Korisnik;

/**
 * Predstavlja model tabele za klasu {@link Korisnik}. Nasledjuje apstraktni model tabele i imeplementira njegove metode.
 * 
 * Ima listu svih uloganih korisnika koji ce se prikazati u tabeli kao i metode za dodavanje 
 * i brisanje korisnika iz ove liste.
 * 
 * @see AbstractTableModel
 * @author Luka Obrenic
 */
public class TableModelKorisnik extends AbstractTableModel{
    
    /**
     * Lista svih ulogovanih korisnika kao lista tipa {@link Korisnik}.
     * @see Korisnik
     */
    List<Korisnik> ulogovaniKorisnici;
    /**
     * Nazivi kolona tabele kao niz stringova.
     */
    String[] naziviKolona = {"Ime", "Prezime"};

    /**
     * Neparametrizovani konstruktor koji inicijalizuje listu ulogovanih korisnika
     */
    public TableModelKorisnik() {
        ulogovaniKorisnici = new ArrayList<>();
    }
    
    
    @Override
    public int getRowCount() {
        return ulogovaniKorisnici.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik s = ulogovaniKorisnici.get(rowIndex);
        
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
     * Dodaje korisnika u listu ulogovanih korisnika i osvezava prikaz tabele na grafickoj formi.
     * 
     * @param k korisnik koji se ulogovao
     */
    public void dodajKorisnika(Korisnik k){
        ulogovaniKorisnici.add(k);
        fireTableDataChanged();
    }

    /**
     * Brise korisnika iz liste ulogovanih korisnika i osvezava prikaz tabele na grafickoj formi.
     * 
     * @param ulogovaniKorisnik koji se izlogovao
     */
    public void obrisiKorisnika(Korisnik ulogovaniKorisnik) {
        ulogovaniKorisnici.remove(ulogovaniKorisnik);
        fireTableDataChanged();
    }
    
}
