/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DefineLigneHoraire.java
 *
 * Created on 09-mai-2011, 21:06:05
 */

package guis;

import data.Bus;
import data.Conducteur;
import data.Ligne;
import data.LigneHoraire;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

/**
 *
 * @author qlecler
 * @author oli
 */

public class DefineLigneHoraire extends javax.swing.JDialog
{
    private Hashtable HashtableBus;
    private Hashtable HashtableConducteur;
    private Hashtable HashtableLigneHoraire;
    private String HashtableBusFilename;
    private String HashtableConducteurFilename;
    private String HashtableLigneHoraireFilename;
    private List<Bus> listeBus;
    private List<Conducteur> listeConducteur;
    private List<Ligne> listeLigne;
    private DefaultComboBoxModel listeBusModel;
    private DefaultComboBoxModel listeConducteurModel;
    private DefaultComboBoxModel listeLigneModel;

    /** Creates new form DefineLigneHoraire */
    public DefineLigneHoraire(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        this.HashtableBus = new Hashtable();
        this.HashtableConducteur = new Hashtable();
        this.HashtableLigneHoraire = new Hashtable();
        this.HashtableBusFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "bus" +
            System.getProperty("file.separator") + "bus.data";
        this.HashtableConducteurFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "conducteur" +
            System.getProperty("file.separator") + "conducteur.data";
        this.HashtableLigneHoraireFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "ligneHoraire" +
            System.getProperty("file.separator") + "ligneHoraire.data";
        this.listeBus = new LinkedList<Bus>();
        this.listeConducteur = new LinkedList<Conducteur>();
        this.listeLigne = new LinkedList<Ligne>();
        this.listeBusModel = (DefaultComboBoxModel) this.BusComboBox.getModel();
        this.listeBusModel.removeAllElements();
        this.listeConducteurModel = (DefaultComboBoxModel) this.ConducteurComboBox.getModel();
        this.listeConducteurModel.removeAllElements();
        this.listeLigneModel = (DefaultComboBoxModel) this.LigneComboBox.getModel();
        this.listeLigneModel.removeAllElements();
        SpinnerModel smDateDepart = new SpinnerDateModel();
        this.DateDepartSpinner.setModel(smDateDepart);
        this.loadDataBus();
        this.loadDataConducteur();
        this.loadDataLigne();
        this.loadDataLigneHoraire();
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
    
    /** Récupère les lignes dans les fichiers correspondents **/
    private void loadDataLigne()
    {
        String filename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "lignes" +
            System.getProperty("file.separator");
        String nomLigne;
        try
        {
            FileReader fr = new FileReader(filename + "liste_fichiers_lignes.txt");
            BufferedReader br = new BufferedReader(fr);
            while((nomLigne = br.readLine()) != null)
            {
                FileInputStream fis = new FileInputStream(filename + nomLigne);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Ligne ligne = new Ligne((Ligne) ois.readObject());
                this.listeLigneModel.addElement(ligne.getNom().toString());
                this.listeLigne.add(ligne);
                ois.close();
              }
            br.close();
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
    
    /** Récupère la Hashtable dans le fichier 'ligneHoraire.data' **/
    private void loadDataLigneHoraire()
    {
        try
        {
            FileInputStream fis = new FileInputStream(this.HashtableLigneHoraireFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.HashtableLigneHoraire = new Hashtable((Hashtable) ois.readObject());
            ois.close();
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
    
    /** Sauvegarde la Hashtable dans le fichier 'ligneHoraire.data' **/
    private void saveDataLigneHoraire()
    {
        if((!this.listeBus.isEmpty()) && (!this.listeConducteur.isEmpty()) &&
            (!this.listeLigne.isEmpty()))
        {
            if((this.listeBusModel.getSelectedItem() != null) &&
                (this.listeConducteurModel.getSelectedItem() != null) &&
                (this.listeLigneModel.getSelectedItem() != null))
            {
                try
                {
                    Bus nouveauBus = new Bus(this.listeBus.get(this.listeBusModel.getIndexOf(
                        this.listeBusModel.getSelectedItem())));
                    Conducteur nouveauConducteur = new Conducteur(this.listeConducteur.get(
                        this.listeConducteurModel.getIndexOf(
                        this.listeConducteurModel.getSelectedItem())));
                    Ligne nouvelleLigne = new Ligne(this.listeLigne.get(
                        this.listeLigneModel.getIndexOf(this.listeLigneModel.getSelectedItem())));
                    LigneHoraire nouvelleLigneHoraire = new LigneHoraire(nouveauBus,
                        nouveauConducteur, nouvelleLigne,
                        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.FRENCH).
                        format(this.DateDepartSpinner.getValue()));
                    this.HashtableLigneHoraire.put(nouvelleLigneHoraire.getDateHeureDepart(),
                        nouvelleLigneHoraire);
                    FileOutputStream fos = new FileOutputStream(this.HashtableLigneHoraireFilename);
                    ObjectOutputStream ois = new ObjectOutputStream(fos);
                    ois.writeObject(this.HashtableLigneHoraire);
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
        BusComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        ConducteurComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        LigneComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        DateDepartSpinner = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("InPrES Bus - Définir une ligne horaire");

        jLabel1.setText("Veuillez choisir un bus :");

        BusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Veuillez choisir un conducteur :");

        ConducteurComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Veuillez choisir une ligne :");

        LigneComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Date de départ :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DateDepartSpinner)
                    .addComponent(LigneComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ConducteurComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BusComboBox, 0, 111, Short.MAX_VALUE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ConducteurComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LigneComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(DateDepartSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        this.saveDataLigneHoraire();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DefineLigneHoraire dialog = new DefineLigneHoraire(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox BusComboBox;
    private javax.swing.JComboBox ConducteurComboBox;
    private javax.swing.JSpinner DateDepartSpinner;
    private javax.swing.JComboBox LigneComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}