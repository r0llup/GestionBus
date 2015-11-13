/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alert;

import data.Ligne;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qlecler
 * @author oli
 */

public final class BusAlertGeneratorBean implements Serializable
{
    private Boolean enMarche;
    private int refMultiple;
    private String ligneConcernee;
    private Vector busAlertListeners;
    private List<Ligne> listeLignes;

    public BusAlertGeneratorBean()
    {
        this.setEnMarche(false);
        this.setRefMultiple(2);
        this.setLigneConcernee("toutes");
        this.busAlertListeners = new Vector();
        this.listeLignes = new LinkedList<Ligne>();
        this.loadLignes();
    }

    public BusAlertGeneratorBean(int refMultiple, String ligneConcernee)
    {
        this.setEnMarche(false);
        this.setRefMultiple(refMultiple);
        this.setLigneConcernee(ligneConcernee);
        this.busAlertListeners = new Vector();
        this.listeLignes = new LinkedList<Ligne>();
        this.loadLignes();
    }

    private void loadLignes()
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
                this.listeLignes.add(ligne);
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
            Logger.getLogger(BusAlertGeneratorBean.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }

    public void init() { this.setEnMarche(true); }

    public void stop() { this.setEnMarche(false); }

    public void run()
    {
        if(this.getEnMarche())
        {
            if(!this.listeLignes.isEmpty())
            {
                for(int i=0; i < BusConstants.NOMBRE_TYPE_INCIDENT; i++)
                {
                    if((new Random().nextInt() % this.getRefMultiple()) == 0)
                    {
                        int typeAccident = (int) (new Random().nextFloat() *
                            (BusConstants.NOMBRE_TYPE_INCIDENT - 1)) + 1;
                        this.ligneConcernee = String.valueOf(this.listeLignes.get((int)
                            (new Random().nextFloat() * (this.listeLignes.size() - 1)) + 1).getNumero());
                        this.notifyBusAlertDetected(typeAccident);
                    }
                }
            }
        }
    }

    public void addBusAlertListener(BusAlertListener bal)
    {
        if(!this.busAlertListeners.contains(bal))
            this.busAlertListeners.addElement(bal);
    }

    public void removeBusAlertListener(BusAlertListener bal)
    {
        if(this.busAlertListeners.contains(bal))
            this.busAlertListeners.removeElement(bal);
    }

    protected void notifyBusAlertDetected(int typeAccident)
    {
        BusAlertEvent e = new BusAlertEvent(this, typeAccident);
        System.out.println(e.getLigneConcernee());
        for(int i=0; i < this.busAlertListeners.size(); i++)
            ((BusAlertListener) this.busAlertListeners.elementAt(i)).NewBusAlertReceived(e);
    }

    public Boolean getEnMarche() { return this.enMarche; }
    public void setEnMarche(Boolean enMarche) { this.enMarche = enMarche; }
    public int getRefMultiple() { return this.refMultiple; }
    public void setRefMultiple(int refMultiple) { this.refMultiple = refMultiple; }
    public String getLigneConcernee() { return this.ligneConcernee; }
    public void setLigneConcernee(String ligneConcernee) { this.ligneConcernee = ligneConcernee; }
}