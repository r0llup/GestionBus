/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModifyConducteur.java
 *
 * Created on 08-mai-2011, 22:35:10
 */

package guis;

import data.Bus;
import data.Conducteur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author qlecler
 * @author oli
 */

public class ModifyConducteur extends javax.swing.JDialog
{
    private Hashtable HashtableConducteur;
    private Hashtable HashtableBus;
    private String HashtableConducteurFilename;
    private String HashtableBusFilename;
    private List<Conducteur> listeConducteur;
    private List<Bus> listeBus;
    private DefaultComboBoxModel listeConducteurModel;
    private DefaultComboBoxModel listeBusModel;

    /** Creates new form ModifyConducteur */
    public ModifyConducteur(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.HashtableConducteur = new Hashtable();
        this.HashtableBus = new Hashtable();
        this.HashtableConducteurFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "conducteur" +
            System.getProperty("file.separator") + "conducteur.data";
        this.HashtableBusFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "bus" +
            System.getProperty("file.separator") + "bus.data";
        this.listeConducteur = new LinkedList<Conducteur>();
        this.listeBus = new LinkedList<Bus>();
        this.listeConducteurModel = (DefaultComboBoxModel) this.ConducteurComboBox.getModel();
        this.listeConducteurModel.removeAllElements();
        this.listeBusModel = (DefaultComboBoxModel) this.BusCombBox.getModel();
        this.listeBusModel.removeAllElements();
        this.loadDataConducteur();
        this.loadDataBus();
    }
    
    /** Récupère la Hashtable dans le fichier 'conducteur.data' **/
    private void loadDataConducteur()
    {
        try
        {
            FileInputStream fis = new FileInputStream(this.HashtableConducteurFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.HashtableConducteur = new Hashtable((Hashtable) ois.readObject());
            ois.close();
            for(Enumeration e = this.HashtableConducteur.keys(); e.hasMoreElements();)
            {
                this.listeConducteurModel.addElement(e.nextElement());
            }
            for(Enumeration e = this.HashtableConducteur.elements(); e.hasMoreElements();)
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

    /** Récupère la Hashtable dans le fichier 'bus.data' **/
    private void loadDataBus()
    {
        try
        {
            FileInputStream fis = new FileInputStream(this.HashtableBusFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.HashtableBus = new Hashtable((Hashtable) ois.readObject());
            ois.close();
            for(Enumeration e = this.HashtableBus.keys(); e.hasMoreElements();)
            {
                this.listeBusModel.addElement(e.nextElement());
            }
            for(Enumeration e = this.HashtableBus.elements(); e.hasMoreElements();)
            {
                this.listeBus.add(new Bus((Bus) e.nextElement()));
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

    /** Sauvegarde la Hashtable dans le fichier 'conducteur.data' **/
    private void saveDataConducteur(Conducteur newConducteur)
    {
        try
        {
            this.HashtableConducteur.put(newConducteur.getMatricule(), newConducteur);
            FileOutputStream fos = new FileOutputStream(this.HashtableConducteurFilename);
            ObjectOutputStream ois = new ObjectOutputStream(fos);
            ois.writeObject(this.HashtableConducteur);
            ois.flush();
            ois.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex);
            System.exit(1);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
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
        jLabel2 = new javax.swing.JLabel();
        BusCombBox = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("InPrES Bus - Modifier un conducteur");

        jLabel1.setText("Veuillez choisir un conducteur :");

        ConducteurComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Veuillez choisir un bus :");

        BusCombBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BusCombBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ConducteurComboBox, 0, 90, Short.MAX_VALUE))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ConducteurComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(BusCombBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jButton1)
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if((!this.listeConducteur.isEmpty()) && (!this.listeBus.isEmpty()))
        {
            if((this.listeConducteurModel.getSelectedItem() != null) &&
                (this.listeBusModel.getSelectedItem() != null))
            {
                this.HashtableConducteur.remove(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())).getMatricule());
                Conducteur newConducteur = this.listeConducteur.get(
                    this.listeConducteurModel.getIndexOf(
                    this.listeConducteurModel.getSelectedItem()));
                newConducteur.setBus(new Bus(this.listeBus.get(
                    this.listeBusModel.getIndexOf(
                    this.listeBusModel.getSelectedItem()))));
                this.saveDataConducteur(newConducteur);
            }
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ModifyConducteur dialog = new ModifyConducteur(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox BusCombBox;
    private javax.swing.JComboBox ConducteurComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}