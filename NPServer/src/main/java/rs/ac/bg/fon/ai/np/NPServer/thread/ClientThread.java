/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.thread;


import java.io.IOException;

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
import rs.ac.bg.fon.ai.np.NPServer.logic.Controller;
import rs.ac.bg.fon.ai.np.NPServer.server.Server;


/**
 *
 * @author student2
 */
public class ClientThread extends Thread {

    private final Socket clientSocket;
    private Sender sender;
    private Receiver receiver;
    private Controller controller;
    private Serviser ulogovaniServiser;
    private Server server;

    public ClientThread(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        sender = new Sender(clientSocket);
        receiver = new Receiver(clientSocket);
        controller = new Controller();
        this.server = server;
    }

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

    public Serviser getUlogovaniServiser() {
        return this.ulogovaniServiser;
    }

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
