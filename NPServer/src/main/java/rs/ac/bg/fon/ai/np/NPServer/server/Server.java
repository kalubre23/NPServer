/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;
import rs.ac.bg.fon.ai.np.NPServer.thread.ClientThread;
import rs.ac.bg.fon.ai.np.NPServer.ui.form.FrmServer;


/**
 * Predstavlja Server koji je zaduzen za prihvatanje novih konekcija sa klijentske strane
 * i dodeljivanje klijentskih niti.
 * 
 * Implementiran je kao nit odnosno nasledjuje klasu Thread. 
 * Sever ima ogranicen broj klijenata koje moze istovremeno opsluzivati.
 * 
 * @see Thread
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class Server extends Thread {

	/**
	 * Serverski socket tipa ServerSocket.
	 */
    private ServerSocket serverSocket;
    /**
     * Lista klijentskih niti kao lista tipa ClientThread.
     */
    private List<ClientThread> clients;
    /**
     * Broj porta na kom server radi kao int.
     */
    private int brPorta;
    /**
     * Maksimalan broj klijenata koje server moze opsluziti kao int.
     */
    private int maxBrKlijenata;
    /**
     * Referenca na serverski GUI.
     */
    private FrmServer sf;

    /**
     * Parametrizovani konstruktor za inicijalizaciju objekta klase Server i dodeljivanja vrednosti atributima.
     * 
     * @param brPorta - Broj porta servera
     * @param maxBrKlijenata - Maksimalan broj klijenata koje server opsluzuje
     * @param sf - Referenca na serversku graficku formu
     */
    public Server(int brPorta, int maxBrKlijenata, FrmServer sf) {
        clients = new ArrayList<>();
        this.sf = sf;
        this.brPorta = brPorta;
        this.maxBrKlijenata = maxBrKlijenata;
    }

    /**
     * Kada se serverska nit pokrene start() metodom, pokrece se run() metoda i nit se izvrsava paralelno sa 
     * svim ostalim nitima.
     * 
     * U while petlji, serverski socket osluskuje nove konekcije sa klijentske strane.
     * Kada dodje do nove konekcije ona se prihvata accept() metodom i dodeljuje objektu klase Socket.
     * Ovaj socket se prosledjuje klijentskoj niti koja se pokrece start() metodom a potom se dodaje u listu svih klijentskih niti.
     * 
     * While petlja se prekida ukoliko se javi izuzetak pri radu sa socket-ima ili je broj klijenta
     * veci od maksimalnog broja. Tada se zavrsava ova metoda i sam rad serverske niti.
     * 
     * @see Thread
     * @see ServerSocket
     * @see Socket
     */
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(brPorta);
            //int trenutniBrojKlijenata = 0;
            while (!interrupted()) {
                if (clients.size() >= maxBrKlijenata) {
                    serverSocket.close();
                    break;
                }
                System.out.println("Waiting for connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
                clients.add(clientThread);
            }
        } catch (IOException ex) {
            System.out.println("Socket zatvoren!");
        }
    }

    /**
     * Proverava da li je serviser ulogovan.
     * 
     * Serviser je ulogovan ako je dodeljen klijentskoj niti.
     * Prolazi kroz sve klijentske niti i vraca da li je serviser vec dodeljen nekoj 
     * odnosno da li je trenutno ulogovan.
     * 
     * @param serviser za koga treba proveriti da li je ulogovan
     * @return
     * <ul> 
     * 		<li> true ako serviser nije ulogovan tj nije dodeljen klijentskoj niti</li>
     * 		<li> false ako je serviser vec ulogovan tj dodeljen nekoj klijentskoj niti</li>
     * </ul>
     */
    public boolean nijeUlogovan(Serviser serviser) {
        for (ClientThread client : clients) {
            if (serviser.equals(client.getUlogovaniServiser())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prekida izvrsavanje serverske niti i zatvara serverski socket.
     * 
     * @see Thread
     * @see ServerSocket
     */
    public void zaustaviServer() {
        interrupt();
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Greska pri zatvaranju serversocketa!");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Povezuje serverski GUI sa klijentskom niti. Prosledjuje ulogovanog servisera
     * serverskoj grafickoj formi.
     * @param ulogovaniServiser koji se dodaje serverskoj formi
     */
    public void dodajServiseraUTabelu(Serviser ulogovaniServiser) {
        sf.dodajServisera(ulogovaniServiser);
    }

    /**
     * Izbacuje klijentsku nit iz liste klijentskih niti. Takodje prosledjuje ulogovanog servisera
     * te klijentske niti serverskoj formi.
     * 
     * @param clientThread koju treba izbaciti iz liste klijentskih niti i proslediti instancu servisera iz nje serverskoj formi
     */
    public void izbaciClientThread(ClientThread clientThread) {
        clients.remove(clientThread);
        sf.obrisiServiseraIzTabele(clientThread.getUlogovaniServiser());
    }
}
