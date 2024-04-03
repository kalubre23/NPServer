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
 *
 * @author Cartman
 */
public class Server extends Thread {

//    private Sender sender;
//    private Receiver receiver;
    private ServerSocket serverSocket;
    private List<ClientThread> clients;
    private int brPorta;
    private int maxBrKlijenata;
    private FrmServer sf;

    public Server(int brPorta, int maxBrKlijenata, FrmServer sf) {
        clients = new ArrayList<>();
        this.sf = sf;
        this.brPorta = brPorta;
        this.maxBrKlijenata = maxBrKlijenata;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(brPorta);
            int trenutniBrojKlijenata = 0;
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

    public boolean nijeUlogovan(Serviser serviser) {
        for (ClientThread client : clients) {
            if (serviser.equals(client.getUlogovaniServiser())) {
                return false;
            }
        }
        return true;
    }

    public void zaustaviServer() {
        interrupt();
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Greska pri zatvaranju serversocketa!");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dodajServiseraUTabelu(Serviser ulogovaniServiser) {
        sf.dodajServisera(ulogovaniServiser);
    }

    public void izbaciClientThread(ClientThread clientThread) {
        clients.remove(clientThread);
        //treba ga maci iz tabele FrmServer
        sf.obrisiServiseraIzTabele(clientThread.getUlogovaniServiser());
    }
}
