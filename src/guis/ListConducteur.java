/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListConducteur.java
 *
 * Created on 07-mai-2011, 22:46:17
 */

package guis;

import data.Conducteur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author qlecler
 * @author oli
 */

public class ListConducteur extends javax.swing.JDialog
{
    private Hashtable HashtableConducteur;
    private String HashtableConducteurFilename;
    private List<Conducteur> listeConducteur;
    private DefaultComboBoxModel listeConducteurModel;

    /** Creates new form ListConducteur */
    public ListConducteur(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.HashtableConducteur = new Hashtable();
        this.HashtableConducteurFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "conducteur" +
            System.getProperty("file.separator") + "conducteur.data";
        this.listeConducteur = new LinkedList<Conducteur>();
        this.listeConducteurModel =
                (DefaultComboBoxModel) this.ConducteurComboBox.getModel();
        this.listeConducteurModel.removeAllElements();
        SpinnerModel smMatricule = new SpinnerNumberModel(1, 1, 999999999, 1);
        SpinnerModel smNumIdBus = new SpinnerNumberModel(1, 1, 999999999, 1);
        this.MatriculeSpinner.setModel(smMatricule);
        this.NumIdBusSpinner.setModel(smNumIdBus);
        this.loadConducteur();
    }

    private void loadConducteur()
    {
        try
        {
            FileInputStream fis = new FileInputStream(this.HashtableConducteurFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.HashtableConducteur = new Hashtable((Hashtable) ois.readObject());
            ois.close();
            for(Enumeration e = this.HashtableConducteur.keys();
                    e.hasMoreElements();)
            {
                this.listeConducteurModel.addElement(e.nextElement());
            }
            for(Enumeration e = this.HashtableConducteur.elements();
                    e.hasMoreElements();)
            {
                this.listeConducteur.add(new Conducteur((Conducteur) e.nextElement()));
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
            System.exit(1);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ListLigne.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ConducteurComboBox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        MatriculeSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        NomTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PrenomTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DateNaissanceTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        NumIdBusSpinner = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("InPrES Bus - Afficher les conducteurs");

        jLabel1.setText("Veuillez choisir un conducteur :");

        ConducteurComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(ConducteurComboBox, 0, 104, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ConducteurComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Matricule :");

        jLabel3.setText("Nom :");

        jLabel4.setText("Prénom :");

        jLabel5.setText("Date de naissance :");

        jLabel6.setText("Numéro d'identification du bus :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NumIdBusSpinner)
                    .addComponent(MatriculeSpinner)
                    .addComponent(NomTextField)
                    .addComponent(PrenomTextField)
                    .addComponent(DateNaissanceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(MatriculeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PrenomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(DateNaissanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(NumIdBusSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Ok");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jButton2)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(!this.listeConducteur.isEmpty())
        {
            if(this.listeConducteurModel.getSelectedItem() != null)
            {
                this.MatriculeSpinner.setValue(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())).getMatricule());
                this.NomTextField.setText(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())).getNom());
                this.PrenomTextField.setText(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())).getPrenom());
                this.DateNaissanceTextField.setText(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())).getDateNaissance());
                if(this.listeConducteur.get(this.listeConducteurModel.getIndexOf(
                    this.listeConducteurModel.getSelectedItem())).getBus() != null)
                {
                    this.NumIdBusSpinner.setValue(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())).getBus().getNumId());
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ListConducteur dialog = new ListConducteur(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ConducteurComboBox;
    private javax.swing.JTextField DateNaissanceTextField;
    private javax.swing.JSpinner MatriculeSpinner;
    private javax.swing.JTextField NomTextField;
    private javax.swing.JSpinner NumIdBusSpinner;
    private javax.swing.JTextField PrenomTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}