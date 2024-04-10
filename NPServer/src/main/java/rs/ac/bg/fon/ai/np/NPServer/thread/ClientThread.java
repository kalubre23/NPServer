/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.thread;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import rs.ac.bg.fon.ai.np.NPCommon.communication.Receiver;
import rs.ac.bg.fon.ai.np.NPCommon.communication.Request;
import rs.ac.bg.fon.ai.np.NPCommon.communication.Response;
import rs.ac.bg.fon.ai.np.NPCommon.communication.Sender;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Automobil;
import rs.ac.bg.fon.ai.np.NPCommon.domain.NalogZaServisiranje;
import rs.ac.bg.fon.ai.np.NPCommon.domain.PokvareniDeo;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;
import rs.ac.bg.fon.ai.np.NPCommon.domain.Vlasnik;
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;
import rs.ac.bg.fon.ai.np.NPServer.server.Server;


/**
 * Predstavlja klijentsku nit na serverskoj strani koja komunicira sa klijentskom stranom.
 * 
 * Prima zahteve klijenta, prosledjuje ih kontroleru aplikacione logike i vraca odgovor nazad klijentu.
 * Implementirana je kao nit, koja radi paralelno sa ostalim klijentskim nitima, nasledjivanjem klase Thread.
 * 
 * @see Controller
 * @see Server
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class ClientThread extends Thread {

	/**
	 * Socket za komunikaciju sa klijentom tipa Socket.
	 * @see Socket
	 */
    private final Socket clientSocket;
    /**
     * Posiljaoc odgovora (Response-a) nazad do klijenta tipa Sender.
     * @see Response
     * @see Sender
     */
    private Sender sender;
    /**
     * Primalac zahteva (Request-a) od klijenta tipa Receiver.
     * @see Request
     * @see Receiver
     */
    private Receiver receiver;
    /**
     * Referenca ka kontroleru aplikacione logike tipa Controller.
     * @see Controller
     */
    private Controller controller;
    /**
     * Objekat servisera koja se dodeljuje klijentskoj niti kako bi se znalo da je taj serviser ulgovan kao
     * i njegovi podaci.
     * @see Serviser
     */
    private Serviser ulogovaniServiser;
    /**
     * Referenca ka serveru tipa Server.
     * @see Server
     */
    private Server server;

    /**
     * Parametrizovani konstruktor za inicijalizaciju objekta klijentske niti i dodelu vrednosti atributima.
     * 
     * @param clientSocket - Socket koji se dodeljuje atributu socket
     * @param server - Instanca servera koji se dodeljuje atributu server
     */
    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        sender = new Sender(clientSocket);
        receiver = new Receiver(clientSocket);
        controller = new Controller();
        this.server = server;
    }

    /**
     * Kada se klijentska nit pokrene start() metodom, pokrece se run() metoda i nit se izvrsava paralelno sa 
     * svim ostalim nitima.
     * 
     * U while petlji, primalac zahteva, preko socketa, osluskuje nove zahteve sa klijentske strane.
     * Kada se primi zahtev onda se na osnovu atributa Operation zahteva odredi ogvoracajuci slucaj u switch-u.
     * Poziva se kontroler aplikacione logike da izvrsi odgovarajucu operaciju, prima se odgovor, dodeljuje se
     * odgovoru koji se na kraju salje nazad do klijenta putem posaljiaoca.
     * 
     * While petlja se prekida ukoliko je nit prekinuta ili se javi izuzetak pri radu sa klijentskim socketom.
     * Tada se zavrsava ova metoda i sam rad klijentske niti.
     * 
     * @see Thread
     * @see Socket
     */
    @Override
    public void run() {
        try {
            while (!interrupted()) {
                try {
                    System.out.println("Stalno pokusava da primi");
                    //receive ce da baca izuzetak ako je klijentski socket zatvoren
                    Request request = (Request) receiver.receive();
                    Response response = new Response();

                    try {
                        switch (request.getOperation()) {
                            case LOGIN:
                                Serviser serviser = (Serviser) request.getArgument();
                                if (server.nijeUlogovan(serviser)) {
                                    response.setResult(controller.login(serviser));
                                    this.ulogovaniServiser = serviser;
                                    server.dodajServiseraUTabelu(this.ulogovaniServiser);
                                } else {
                                    throw new Exception("User je vec prijavljen.");
                                }
                                break;
                            case UCITAJ_LISTU_MARKI:
                                response.setResult(controller.ucitajListuMarki());
                                break;
                            case SACUVAJ_AUTOMOBIL:
                                Automobil automobilNovi = (Automobil) request.getArgument();

                                controller.sacuvajAutomobil(automobilNovi);
                                response.setResult(automobilNovi);
                                break;
                            case PRONADJI_AUTOMOBILE:
                                Automobil automobilPretraga = (Automobil) request.getArgument();
                                List<Automobil> automobiliPretraga = controller.pronadjiAutomobile(automobilPretraga);
                                response.setResult(automobiliPretraga);
                                break;
                            case IZMENI_AUTOMOBIL:
                                Automobil automobilIzmena = (Automobil) request.getArgument();
                                controller.izmeniAutomobil(automobilIzmena);
                                response.setResult(automobilIzmena);
                                break;
                            case OBRISI_AUTOMOBIL:
                                Automobil automobilBrisanje = (Automobil) request.getArgument();
                                controller.obrisiAutomobil(automobilBrisanje);
                                //response.setResult(automobilBrisanje);
                                break;
                            case UCITAJ_LISTU_DELOVA_AUTOMOBILA:
                                response.setResult(controller.ucitajListuDelovaAutomobila());
                                break;
                            case SACUVAJ_POKVAREN_DEO:
                                PokvareniDeo pokvareniDeo = (PokvareniDeo) request.getArgument();
                                controller.sacuvajPokvarenDeo(pokvareniDeo);
                                response.setResult(pokvareniDeo);
                                break;
                            case PRONADJI_POKVARENE_DELOVE:
                                PokvareniDeo pd = (PokvareniDeo) request.getArgument();
                                List<PokvareniDeo> pdPretraga = controller.pronadjiPokvareneDelove(pd);
                                response.setResult(pdPretraga);
                                break;
                            case IZMENI_POKVAREN_DEO:
                                PokvareniDeo pdIzmena = (PokvareniDeo) request.getArgument();
                                controller.izmeniPokvarenDeo(pdIzmena);
                                response.setResult(pdIzmena);
                                break;
                            case OBRISI_POKVAREN_DEO:
                                PokvareniDeo pdBrisanje = (PokvareniDeo) request.getArgument();
                                controller.obrisiPokvarenDeo(pdBrisanje);
                                break;
                            case SACUVAJ_NALOG_ZA_SERVISIRANJE:
                                NalogZaServisiranje nalogZaServisiranje = (NalogZaServisiranje) request.getArgument();
                                controller.sacuvajNalogZaServisiranje(nalogZaServisiranje);
                                response.setResult(nalogZaServisiranje);
                                break;
                            case PRONADJI_NALOGE_ZA_SERVISIRANJE:
                                NalogZaServisiranje nalogPretraga = (NalogZaServisiranje) request.getArgument();
                                List<NalogZaServisiranje> naloziPretraga = controller.pronadjiNalogeZaServisiranje(nalogPretraga);
                                response.setResult(naloziPretraga);
                                break;
                            case OBRISI_NALOG_ZA_SERVISIRANJE:
                                NalogZaServisiranje nalogBrisanje = (NalogZaServisiranje) request.getArgument();
                                controller.obrisiNalog(nalogBrisanje);
                                break;
                            case SACUVAJ_VLASNIKA:
                                Vlasnik vlasnikDodaj = (Vlasnik) request.getArgument();
                                controller.sacuvajVlasnika(vlasnikDodaj);
                                response.setResult(vlasnikDodaj);
                                break;
                            case PRONADJI_VLASNIKE:
                                Vlasnik vlasnikPretraga = (Vlasnik) request.getArgument();
                                List<Vlasnik> vlasniciPretraga = controller.pronadjiVlasnike(vlasnikPretraga);
                                response.setResult(vlasniciPretraga);
                                break;
                            case IZMENI_VLASNIKA:
                                Vlasnik vlasnikIzmena = (Vlasnik) request.getArgument();
                                controller.izmeniVlasnika(vlasnikIzmena);
                                response.setResult(vlasnikIzmena);
                                break;
                            case OBRISI_VLASNIKA:
                                Vlasnik vlasnikBrisanje = (Vlasnik) request.getArgument();
                                controller.obrisiVlasnika(vlasnikBrisanje);
                                break;
                            case LOGOUT:
                                //Serviser serviserLogout = (Serviser) request.getArgument();
                                zatvoriSocket();
                                break;

                        }
                        //ovaj prvi catch sluzi da uhvati izuzetke so i vrati ih klijentu
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Unutrasnji catch ga je uhvatio");
                        response.setException(e);
                    }
                    sender.send(response);
                    //ovaj drugi catch sluzi za izuzetke koje baca receive();
                } catch (Exception ex) {
                    System.out.println("Spoljasnji catch ga je uhvatio");
                    ex.printStackTrace();
                    throw ex;
                }
            }
        } catch (Exception ex) {
            //klijent je ugasio soket
            // tako da treba prekinuti run i zatvoriti i ovaj socket
            zatvoriSocket();
        }

    }

    /**
     * Vraca ulogvanog servisera kao Serviser.
     * @see Serviser
     * @return instancu ulogovanog servisera tipa Serviser
     */
    public Serviser getUlogovaniServiser() {
        return this.ulogovaniServiser;
    }

    /**
     * Prekida klijentsku nit, zatvara socket i javlja serveru da 
     * izbaci ovu klijentsku nit iz liste svih klijentskih niti.
     * 
     * Poziva se kada se klijent izloguje ili kada se zatvori socket na klijentskoj strani.
     */
    private void zatvoriSocket() {
        if (!interrupted()) {
            interrupt();
            System.out.println("Uspesno prekinut clientThread!");
        }
        try {
            if (!clientSocket.isClosed() && clientSocket != null) {
                this.clientSocket.close();
                System.out.println("Uspesno zatvoren socket na serverskoj strani!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Ne moze da se zatvori socket u ClientThread-u!");
        }
        //javi serveru da izbaci ovog clientThread iz clients
        izbaciClientThread();
    }

    private void izbaciClientThread() {
        server.izbaciClientThread(this);
    }

}
