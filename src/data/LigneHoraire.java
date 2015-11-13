/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.Serializable;

/**
 *
 * @author qlecler
 * @author oli
 */

public final class LigneHoraire implements Serializable
{
    private Bus bus;
    private Conducteur conducteur;
    private Ligne ligne;
    private String dateHeureDepart;

    public LigneHoraire(Bus bus, Conducteur conducteur, Ligne ligne, String dateHeureDepart)
    {
        this.setBus(bus);
        this.setConducteur(conducteur);
        this.setLigne(ligne);
        this.setDateHeureDepart(dateHeureDepart);
    }

    public LigneHoraire(LigneHoraire ligneHoraire)
    {
        this.setBus(ligneHoraire.getBus());
        this.setConducteur(ligneHoraire.getConducteur());
        this.setLigne(ligneHoraire.getLigne());
        this.setDateHeureDepart(ligneHoraire.getDateHeureDepart());
    }

    public Bus getBus() { return this.bus; }
    public Conducteur getConducteur() { return this.conducteur; }
    public Ligne getLigne() { return this.ligne; }
    public String getDateHeureDepart() { return this.dateHeureDepart; }
    public void setBus(Bus bus) { this.bus = bus; }
    public void setConducteur(Conducteur conducteur) { this.conducteur = conducteur; }
    public void setLigne(Ligne ligne) { this.ligne = ligne; }
    public void setDateHeureDepart(String dateHeureDepart)
        { this.dateHeureDepart = dateHeureDepart; }
}