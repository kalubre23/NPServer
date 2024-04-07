/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.ac.bg.fon.ai.np.NPServer.ui.form;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

/**
 * Graficka forma na serverskoj strani za unos broja porta i maksimalnog broja korisnika.
 * 
 * @author Luka Obrenic
 * @since 1.0.0
 */
public class FrmKonfiguracija extends javax.swing.JDialog {

	/**
	 * Referenca ka serverskoj grafickoj formi tipa FrmServer.
	 * 
	 */
    FrmServer gf;

    /**
     * Kreira novu formu FrmKonfiguracija
     */
    public FrmKonfiguracija(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.gf = (FrmServer) parent;
    }

    /**
     * Inicijalizacija grafickih komponenata forme. Ne dirati ovaj kod!
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtBrojPorta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtMaxBrKlijenata = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Unesi broj porta: ");

        jButton1.setText("Sacuvaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Maksimalan broj klijenata");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBrojPorta, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(txtMaxBrKlijenata))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBrojPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaxBrKlijenata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metoda koja se poziva klikom na dugme sacuvaj.
     * 
     * Proverava da li su uneti broj porta i klijenata u odgovarajucem formatu. Ako jesu 
     * prosledjuju se serversokj formi.
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (!txtBrojPorta.getText().matches("^[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Nevalidan broj porta!", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!dostupanPort(Integer.parseInt(txtBrojPorta.getText()))) {
            JOptionPane.showMessageDialog(this, "Port nije dostupan!", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!txtMaxBrKlijenata.getText().matches("^[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Nepravilan unos za maksimalan broj klijenata!", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int brojPorta = Integer.parseInt(txtBrojPorta.getText());
        int maxBrKlijenata = Integer.parseInt(txtMaxBrKlijenata.getText());
        if (maxBrKlijenata <= 0) {
            JOptionPane.showMessageDialog(this, "Broj klijenata mora biti veci od 0!", "Greska", JOptionPane.ERROR_MESSAGE);
            return;
        }
        gf.setBrPorta(brojPorta);
        gf.setMaxBrKlijenata(maxBrKlijenata);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtBrojPorta;
    private javax.swing.JTextField txtMaxBrKlijenata;
    // End of variables declaration//GEN-END:variables

    /**
     * Vraca da li je broj porta dostupan.
     * 
     * 
     * @param port kao int.
     * @return
     * <ul>
     * 		<li> true ako je izmedju 1024 i 65000 i ako nije zauzet</li>
     * 		<li> false ako nije izmedju 1024 i 65000 ili ako je zauzet</li>
     * </ul>
     * 
     */
    private boolean dostupanPort(int port) {
        if (port < 1024 || port > 65000) {
            return false;
        }
        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
           
        } finally {
            if (ds != null) {
                ds.close();
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                }
            }
        }
        
        return false;
    }
}
