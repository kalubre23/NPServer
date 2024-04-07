/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.ui.form;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import rs.ac.bg.fon.ai.np.NPCommon.domain.Serviser;
import rs.ac.bg.fon.ai.np.NPServer.server.Server;
import rs.ac.bg.fon.ai.np.NPServer.ui.components.TableModelServiser;


/**
 * Glavna serverska graficka forma na kojoj se pokrece/zaustavlja server i prikazuju svi ulogovani serviseri.
 * 
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class FrmServer extends javax.swing.JFrame {

	/**
	 * Referenca ka serveru tipa Server.
	 * @see Server
	 */
    private Server server;
    /**
     * Broj porta servera.
     */
    private int brPorta;
    /**
     * Maksimalan broj klijenata na serveru.
     */
    private int maxBrKlijenata;

    /**
     * Postavlja novu vrednost broja porta.
     * 
     * @param brPorta nova vrednost broja porta kao int.
     */
    public void setBrPorta(int brPorta) {
        this.brPorta = brPorta;
        //System.out.println("br porta je: " + this.brPorta);
        lblBrPorta.setText(String.valueOf(this.brPorta));
    }

    /**
     * Postavlja novu vrednost maksimalnog broja klijenata.
     * 
     * @param maxBrKlijenata nova vrednost maksimalnog broja korisnika kao int
     */
    public void setMaxBrKlijenata(int maxBrKlijenata) {
        this.maxBrKlijenata = maxBrKlijenata;
        lblmaxBrKlijenata.setText(String.valueOf(this.maxBrKlijenata));
    }
    
    /**
     * Kreira novu formu FrmServer
     */
    public FrmServer() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Server program");
        prepareView();
    }

    /**
     * Inicijalizacija grafickih komponenata forme. Ne dirati ovaj kod!
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblStatusServera = new javax.swing.JLabel();
        btnPokreniServer = new javax.swing.JButton();
        btnZaustaviServer = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblServiseri = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        lblBrPorta = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        lblmaxBrKlijenata = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        meniKonfiguracija = new javax.swing.JMenu();
        menuItemKonfig = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Status servera:");

        lblStatusServera.setText("jLabel2");

        btnPokreniServer.setText("Pokreni server");
        btnPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokreniServerActionPerformed(evt);
            }
        });

        btnZaustaviServer.setText("Zaustavi server");
        btnZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZaustaviServerActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Ulogovani serviseri");

        tblServiseri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblServiseri);

        jLabel4.setText("Broj porta:");

        lblBrPorta.setText("jLabel5");

        jLabel5.setText("Maksimalan broj klijenata");

        lblmaxBrKlijenata.setText("jLabel6");

        meniKonfiguracija.setText("Konfiguracija");

        menuItemKonfig.setText("Broj porta i broj klijenata");
        menuItemKonfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemKonfigActionPerformed(evt);
            }
        });
        meniKonfiguracija.add(menuItemKonfig);

        jMenuBar1.add(meniKonfiguracija);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPokreniServer)
                        .addGap(18, 18, 18)
                        .addComponent(btnZaustaviServer))
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblStatusServera))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblBrPorta))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblmaxBrKlijenata)))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addComponent(jSeparator2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblmaxBrKlijenata))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblBrPorta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblStatusServera))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPokreniServer)
                    .addComponent(btnZaustaviServer))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metoda koja se poziva kada se klikne na stavku menija konfikuracija.
     * 
     * Otvara formu za konfiguraciju tj unos broja porta i maksimalnog broja klijenata
     * 
     * @see FrmKonfiguracija
     * @param evt
     */
    private void menuItemKonfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemKonfigActionPerformed
        // TODO add your handling code here:
        JDialog frmKonfig = new FrmKonfiguracija(this, true);
        frmKonfig.setLocationRelativeTo(this);
        frmKonfig.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frmKonfig.setVisible(true);
    }//GEN-LAST:event_menuItemKonfigActionPerformed

    /**
     * Metoda koja se poziva kada se klikne na dugme pokreni server.
     * 
     * Poziva se metoda za pokretanje servera. Moze biti pozvana samo ukoliko su uneti broj porta i maksimalni broj klijenata.
     * @param evt
     */
    private void btnPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokreniServerActionPerformed
        // TODO add your handling code here:
        if(lblBrPorta.getText().equals("nije unet")){
            JOptionPane.showMessageDialog(this, "Nisu uneti broj porta i broj podrzanih klijenata!", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        pokreniServer();
    }//GEN-LAST:event_btnPokreniServerActionPerformed

    /**
     * Metoda koja se poziva kada se klikne na dugme zaustavi server.
     * 
     * Poziva se metoda koja gasi server.
     * 
     * @param evt
     */
    private void btnZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZaustaviServerActionPerformed
        // TODO add your handling code here:
        ugasiServer();
    }//GEN-LAST:event_btnZaustaviServerActionPerformed

    /**
     * Main metoda za kreiranje i prikaz serverske forme.
     * 
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPokreniServer;
    private javax.swing.JButton btnZaustaviServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblBrPorta;
    private javax.swing.JLabel lblStatusServera;
    private javax.swing.JLabel lblmaxBrKlijenata;
    private javax.swing.JMenu meniKonfiguracija;
    private javax.swing.JMenuItem menuItemKonfig;
    private javax.swing.JTable tblServiseri;
    // End of variables declaration//GEN-END:variables

    /**
     * Priprema formu za prikaz.
     * 
     * Postavlja sve pocetne vrednosti atributa komponenti pre nego sto se forma prikaze.
     */
    private void prepareView() {
        lblBrPorta.setText("nije unet");
        lblmaxBrKlijenata.setText("nije uneto");
        lblStatusServera.setText("Server nije pokrenut!");
        lblStatusServera.setForeground(Color.red);
        btnZaustaviServer.setEnabled(false);
        
        tblServiseri.setModel(new TableModelServiser());
    }

    /**
     * Kreira novu instancu servera kojoj prosledjuje broj porta, maksimalni broj klilenata 
     * i referencu na samu formu. Nakon toga se pokrece nit i azurira prikaz forme.
     * 
     * @see Server
     */
    private void pokreniServer() {
        server = new Server(this.brPorta, this.maxBrKlijenata, this);
        server.start();
        lblStatusServera.setText("Server je pokrenut!");
        lblStatusServera.setForeground(Color.GREEN);
        btnPokreniServer.setEnabled(false);
        btnZaustaviServer.setEnabled(true);
        meniKonfiguracija.setEnabled(false);
    }

    /**
     * Zaustavlja serversku nit i azurira prikaz forme.
     * 
     * @see Server
     */
    private void ugasiServer() {
        server.zaustaviServer();
        btnZaustaviServer.setEnabled(false);
        btnPokreniServer.setEnabled(true);
        lblStatusServera.setText("Server nije pokrenut!");
        lblStatusServera.setForeground(Color.red);
        meniKonfiguracija.setEnabled(true);
    }

    /**
     * Dodaje servisera u listu svih ulogovanih servisera u table modelu.
     * 
     * @param ulogovaniServiser koji se dodaje u tabelu, tipa Serviser.
     * @see Serviser
     */
    public void dodajServisera(Serviser ulogovaniServiser) {
        ((TableModelServiser)tblServiseri.getModel()).dodajServisera(ulogovaniServiser);
    }

    /**
     * Brise servisera iz liste svih ulogovanih servisera u table modelu.
     * 
     * @param ulogovaniServiser koji se brise iz tabele, tipa Serviser.
     * @see Serviser
     */
    public void obrisiServiseraIzTabele(Serviser ulogovaniServiser) {
        ((TableModelServiser)tblServiseri.getModel()).obrisiServisera(ulogovaniServiser);
    }
}
