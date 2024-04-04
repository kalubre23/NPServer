/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.domain.DomenskiObjekat;

/**
 *
 * @author Cartman
 */
public class DatabaseBroker {

    private final Connection connection;

    public DatabaseBroker(Connection connection) {
        this.connection = connection;
    }

    public DomenskiObjekat vratiJednog(DomenskiObjekat objekat) throws SQLException {
        try {
            String query = "SELECT " + objekat.vratiVrednostiZaSelect() + " FROM " + objekat.vratiNazivTabele() + " WHERE " + objekat.vratiUslovZaJednog();
            System.out.println("Upit: " + query);

            //Pravljenje objekta koji je odgovoran za izvrsavanje upita
            PreparedStatement statement = connection.prepareStatement(query);

            objekat.postaviVrednostiZaSelect(statement, objekat);
//            statement.setString(1, serviser.getUsername());
//            statement.setString(2, serviser.getPassword());
            //izvsi upit
            ResultSet rs = statement.executeQuery();

            //pristup rezultatima upita
            objekat = objekat.vratiJednog(rs, objekat);
//            if (rs.next()) {
//                serviser.setServiserID(rs.getLong("serviserid"));
//                serviser.setIme(rs.getString("ime"));
//                serviser.setPrezime(rs.getString("prezime"));
//            } else {
//                serviser = null;
//            }
            //oslobadjanje resursa

            System.out.println("U databaseBrokeru je objekat trenutno: " + objekat);
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitavanje objekta Serviser iz baze!");
            return objekat;
        } catch (SQLException ex) {
            System.out.println("Objekat Serviser nije uspesno ucitan iz baze!");
            ex.printStackTrace();
            throw ex;
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public List<DomenskiObjekat> vratiSve(DomenskiObjekat obj) throws SQLException {
        try {
            List<DomenskiObjekat> listaSvih;
            String upit = "SELECT " + obj.vratiVrednostiZaSelect() + " FROM " + obj.vratiNazivTabele();
            System.out.println("Upit: " + upit);
            //Pravljenje objekta koji je odgovoran za izvrsavanje upita
            Statement statement = connection.createStatement();
            //izvsi upit
            ResultSet rs = statement.executeQuery(upit);
            //pristup rezultatima upita
            listaSvih = obj.vratiListuSvih(rs);
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitavanje objekata klase "+obj.getClass()+" iz baze!");
            return listaSvih;
        } catch (SQLException ex) {
            System.out.println("Objekti klase "+obj.getClass()+" nisu uspesno ucitani iz baze!");
            ex.printStackTrace();
            throw ex;
        }
    }

    public void sacuvaj(DomenskiObjekat object) throws SQLException {
        try {
            String upit = "INSERT INTO " + object.vratiNazivTabele() + "(" + object.getKoloneZaInsert() + ")" + " VALUES " + "(" + object.vratiVrednostiZaInsert() + ")";
            System.out.println("Upit: " + upit);

            PreparedStatement statement = connection.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
            object.postaviVrednostiZaInsert(statement, object);

            int result = statement.executeUpdate();
            System.out.println("Result: " + result);
            System.out.println("Uspesno dodavanje objekta " + object.getClass() + " u bazu!");

            if (object.sadrziAutoIncrementPK()) {
                ResultSet rsID = statement.getGeneratedKeys();
                if (rsID.next()) {
                    object.postaviAutoIncrementPrimaryKey(rsID.getInt(1));
                }
                rsID.close();
            }

            System.out.println("Objekat :" + object);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno dodavanje objekta " + object.getClass() + " u bazu!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }

    }

    public List<DomenskiObjekat> vratiViseSlozenihSaUslovom(DomenskiObjekat object) throws Exception {
        try {
            String upit1 = "SELECT " + object.vratiVrednostiZaSelect() + " FROM "
                    + object.vratiNazivTabele() + " WHERE " + object.vratiUslovZaVise();
            System.out.println("Upit: " + upit1);

            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = statement.executeQuery(upit1);
            if (!rs.next()) {
                throw new Exception("Ne postoje takvi u bazi");
            }
            rs.beforeFirst();
            List<DomenskiObjekat> lista;
            lista = object.vratiListuSvih(rs);
            rs.close();

            //sad treba da se vrati lista vezzanih objekata
            for (DomenskiObjekat obj : lista) {
                String upit2 = "SELECT " + obj.vratiVezaniObjekat().vratiVrednostiZaSelect()
                        + " FROM " + obj.vratiVezaniObjekat().vratiNazivTabele() + " WHERE " + obj.vratiUslovZaVezani();
                System.out.println("Upit za vezani: " + upit2);
                try {
                    rs = statement.executeQuery(upit2);
                } catch (java.sql.SQLSyntaxErrorException e) {
                    e.printStackTrace();
                    throw new Exception("Objekat " + obj.getClass() + " Ne sadrzi stavke objekta " + obj.vratiVezaniObjekat().getClass());
                }

                obj.napuni(rs);

            }
            rs.close();
            statement.close();
            return lista;
        } catch (Exception ex) {
            System.out.println("Neuspesno ucitavanje objekta " + object.getClass() + " iz baze");
            ex.printStackTrace();
            throw ex;
        }
    }

    public void izmeniSlozeni(DomenskiObjekat object) throws SQLException {
        try {
            String upit1 = "UPDATE " + object.vratiNazivTabele()
                    + " SET " + object.vratiVrednostiZaUpdate()
                    + " WHERE " + object.vratiUslovZaUpdate();
            System.out.println("Upit za update: " + upit1);

            Statement statement = connection.createStatement();
            int result1 = statement.executeUpdate(upit1);
            System.out.println("Affected rows update: " + result1);

            //sledece sto moram da uradim je da obrisem sve kvarove sa uslovom
            //pa onda da ih ponovo ubacim
            String upit2 = "DELETE FROM " + object.vratiVezaniObjekat().vratiNazivTabele()
                    + " WHERE " + object.vratiUslovZaUpdate();

            int result2 = statement.executeUpdate(upit2);
            System.out.println("Affected rows delete: " + result2);

            //onda ponovo ubacis sve
        } catch (SQLException ex) {
            System.out.println("Greska pri izmeni slozenog objekta " + object.getClass());
            ex.printStackTrace();
            throw ex;
        }
    }

    public void obrisi(DomenskiObjekat object) throws SQLException {
        try {
            String upit = "DELETE FROM " + object.vratiNazivTabele()
                    + " WHERE " + object.vratiUslovZaUpdate();
            System.out.println("Upit: "+upit);

            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(upit);
            System.out.println("Affected rows delete: " + result);
            System.out.println("Uspesno obrisan objekat " + object.getClass() + " iz tabele " + object.vratiNazivTabele());
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska pri prisanju objekta iz tabele " + object.vratiNazivTabele());
            throw ex;
        }
    }

    public List<DomenskiObjekat> vratiViseSaUslovom(DomenskiObjekat object) throws SQLException {
        try {
            String upit = "SELECT " + object.vratiVrednostiZaSelect()
                    + " FROM " + object.vratiNazivTabele()
                    + " WHERE " + object.vratiUslovZaVise();
            System.out.println("Upit: "+upit);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            List<DomenskiObjekat> lista = object.vratiListuSvih(rs);

            rs.close();
            statement.close();
            if (lista == null || lista.isEmpty()) {
                System.out.println("LISTA JE PRAZNA!!!");
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Neuspesno ucitavanje objekta " + object.getClass() + " iz baze");
            ex.printStackTrace();
            throw ex;
        }
    }

    public void izmeni(DomenskiObjekat object) throws SQLException {
        try {
            String upit = "UPDATE " + object.vratiNazivTabele() +
                    " SET "+object.vratiVrednostiZaUpdate() +
                    " WHERE "+object.vratiUslovZaUpdate();
            
            System.out.println("Upit: "+upit);
            PreparedStatement statement = connection.prepareStatement(upit);
            object.postaviVrednostiZaUpdate(statement, object);
            int result = statement.executeUpdate();
            System.out.println("Uspesno izmenjen objekat klase "+object.getClass());
            System.out.println("Rows updated: "+result);
            
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Greska pri izmeni objekta klase: "+object.getClass());
            ex.printStackTrace();
            throw ex;
        }
    }
}
