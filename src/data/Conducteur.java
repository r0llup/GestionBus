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

public final class Conducteur extends Personne implements Serializable
{
    private int matricule;
    private Bus bus;

    public Conducteur(String nom, String prenom, String dateNaissance, int matricule, Bus bus)
    {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
        this.setMatricule(matricule);
        this.setBus(bus);
    }

    public Conducteur(Conducteur conducteur)
    {
        this.setNom(conducteur.getNom());
        this.setPrenom(conducteur.getPrenom());
        this.setDateNaissance(conducteur.getDateNaissance());
        this.setMatricule(conducteur.getMatricule());
        this.setBus(conducteur.getBus());
    }

    public int getMatricule() { return this.matricule; }
    public Bus getBus() { return this.bus; }
    @Override public String getIdentifiant() { return String.valueOf(matricule); }
    public void setMatricule(int matricule) { this.matricule = matricule; }
    public void setBus(Bus bus) { this.bus = bus; }
}