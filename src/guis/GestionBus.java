/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GestionBus.java
 *
 * Created on 28-avr.-2011, 23:47:12
 */

package guis;

import alert.BusAlertAnalyseBean;
import alert.BusAlertGeneratorBean;
import alert.BusAlertSenderBean;
import data.Ligne;
import data.LigneHoraire;
import data.Segment;
import java.beans.Beans;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author qlecler
 * @author oli
 */

public class GestionBus extends javax.swing.JFrame
{
    private About AboutDial;
    private List<Ligne> listeLigne;
    private DefaultTreeModel treeLigneModel;
    private DefaultMutableTreeNode treeLigneRoot;
    private Hashtable HashtableLigneHoraire;
    private String HashtableLigneHoraireFilename;
    private DefaultTableModel tableLigneHoraireModel;
    private BusAlertGeneratorBean beanGeneratorAlertBus;
    private BusAlertAnalyseBean beanAnalyseAlertBus;
    private BusAlertSenderBean beanSenderAlertBus;

    /** Creates new form GestionBus */
    public GestionBus()
    {
        initComponents();
        Login LoginDial = new Login(this, "InPrES Bus - Connexion", 400, 200, true);
        LoginDial.setVisible(true);
        this.AboutDial = new About(this, true);
        this.listeLigne = new LinkedList<Ligne>();
        this.treeLigneModel = (DefaultTreeModel) this.LigneTree.getModel();
        this.treeLigneRoot = new DefaultMutableTreeNode("parcours");
        this.treeLigneModel.setRoot(this.treeLigneRoot);
        this.HashtableLigneHoraire = new Hashtable();
        this.HashtableLigneHoraireFilename = System.getProperty("user.dir") +
            System.getProperty("file.separator") + "serializable" +
            System.getProperty("file.separator") + "ligneHoraire" +
            System.getProperty("file.separator") + "ligneHoraire.data";
        this.tableLigneHoraireModel = (DefaultTableModel) this.LigneHoraireTable.getModel();
        this.beanSenderAlertBus = new BusAlertSenderBean();
        this.beanAnalyseAlertBus = new BusAlertAnalyseBean(this, true);
        this.beanAnalyseAlertBus.addPropertyChangeListener(this.beanSenderAlertBus);
        this.loadDataLigne();
        this.loadDataLigneHoraire();
        this.loadBean();
    }

    private void loadBean()
    {
        try
        {
            BusAlertGeneratorBean bagb =
                (BusAlertGeneratorBean) Beans.instantiate(null, "alert.BusAlertGeneratorBean");
            bagb.addBusAlertListener(this.beanAnalyseAlertBus);
            bagb.init();
            bagb.run();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
            System.exit(1);
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(1);
        }
    }

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
                this.listeLigne.add(ligne);
                ois.close();
              }
            br.close();
            if(!this.listeLigne.isEmpty())
            {
                for(Ligne l:this.listeLigne)
                {
                    DefaultMutableTreeNode racine = new DefaultMutableTreeNode(l.getNom());
                    for(Segment s:l.getListeSegment())
                    {
                        DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(
                            s.getArret1() + " - " + s.getArret2());
                        DefaultMutableTreeNode feuille =
                            new DefaultMutableTreeNode(s.getDuree());
                        noeud.add(feuille);
                        racine.add(noeud);
                    }
                    this.treeLigneRoot.add(racine);
                }
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

    /** Récupère la Hashtable dans le fichier 'ligneHoraire.data' **/
    private void loadDataLigneHoraire()
    {
        try
        {
            FileInputStream fis = new FileInputStream(this.HashtableLigneHoraireFilename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.HashtableLigneHoraire = new Hashtable((Hashtable) ois.readObject());
            ois.close();
            for(Enumeration e = this.HashtableLigneHoraire.elements(); e.hasMoreElements();)
            {
                LigneHoraire horaireLigne = new LigneHoraire((LigneHoraire) e.nextElement());
                Object [] RowLigneHoraire = {horaireLigne.getBus().getNumId(),
                    horaireLigne.getConducteur().getMatricule(),
                    horaireLigne.getLigne().getNom(),
                    horaireLigne.getDateHeureDepart()};
                    this.tableLigneHoraireModel.addRow(RowLigneHoraire);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        LigneTree = new javax.swing.JTree();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        IncidentTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LigneHoraireTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        DefineLigneMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        AboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("InPrES Bus");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setText("*** InPrES Bus ***");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(jLabel1)
                .addContainerGap(461, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\qlecler\\Documents\\NetBeansProjects\\GestionBus\\picture\\Gare 30.jpg")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Trafic des lignes");

        jScrollPane1.setViewportView(LigneTree);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        IncidentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date-heure signalement", "Ligne", "Type", "Date-heure fin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(IncidentTable);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel4.setText("Incidents");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(221, 221, 221))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel3.setText("Horaire en cours");

        LigneHoraireTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus", "Conducteur", "Ligne", "Date de départ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(LigneHoraireTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel3)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jMenu6.setText("Fichier");

        jMenuItem14.setText("Quitter");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuBar1.add(jMenu6);

        jMenu1.setText("Bus");

        jMenuItem3.setText("Nouveau");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("En/Hors service");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem5.setText("Liste");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Conducteurs");

        jMenuItem6.setText("Nouveau");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Affectations");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);
        jMenu2.add(jSeparator3);

        jMenuItem8.setText("Liste");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Lignes");

        DefineLigneMenuItem.setText("Définir");
        DefineLigneMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DefineLigneMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(DefineLigneMenuItem);

        jMenuItem1.setText("Modifier arrêt");
        jMenu3.add(jMenuItem1);
        jMenu3.add(jSeparator1);

        jMenuItem2.setText("Liste");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Horaires");

        jMenuItem9.setText("Ajouter une ligne horaire");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);
        jMenu4.add(jSeparator4);

        jMenuItem10.setText("Lister les lignes horaires");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Lister les incidents");
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Aide");

        jMenuItem12.setText("Prise de notes");
        jMenu5.add(jMenuItem12);

        jMenuItem13.setText("Afficher le log");
        jMenu5.add(jMenuItem13);
        jMenu5.add(jSeparator5);

        AboutMenuItem.setText("À propos");
        AboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutMenuItemActionPerformed(evt);
            }
        });
        jMenu5.add(AboutMenuItem);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        // TODO add your handling code here:
        DefineLigne LigneDefine = new DefineLigne(this, true);
        LigneDefine.setVisible(true);
    }//GEN-LAST:event_jMenu7MouseClicked

    private void AboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutMenuItemActionPerformed
        // TODO add your handling code here:
        this.AboutDial.setVisible(true);
    }//GEN-LAST:event_AboutMenuItemActionPerformed

    private void DefineLigneMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DefineLigneMenuItemActionPerformed
        // TODO add your handling code here:
        DefineLigne LigneDefine = new DefineLigne(this, true);
        LigneDefine.setVisible(true);
    }//GEN-LAST:event_DefineLigneMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        ListLigne LigneList = new ListLigne(this, true);
        LigneList.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        DefineBus BusDefine = new DefineBus(this, true);
        BusDefine.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ListBus BusList = new ListBus(this, true);
        BusList.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        DefineConducteur ConducteurDefine = new DefineConducteur(this, true);
        ConducteurDefine.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        ListConducteur ConducteurList = new ListConducteur(this, true);
        ConducteurList.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        ModifyBus BusModify = new ModifyBus(this, true);
        BusModify.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        ModifyConducteur ConducteurModify = new ModifyConducteur(this, true);
        ConducteurModify.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        DefineLigneHoraire LigneHoraireDefine = new DefineLigneHoraire(this, true);
        LigneHoraireDefine.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        ListLigneHoraire LigneHoraireList = new ListLigneHoraire(this, true);
        LigneHoraireList.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionBus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutMenuItem;
    private javax.swing.JMenuItem DefineLigneMenuItem;
    private javax.swing.JTable IncidentTable;
    private javax.swing.JTable LigneHoraireTable;
    private javax.swing.JTree LigneTree;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    // End of variables declaration//GEN-END:variables

}