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

public class Personne implements EstIdentifiable, Serializable
{
    protected String nom;
    protected String prenom;
    protected String dateNaissance;

    public String getNom() { return this.nom; }
    public String getPrenom() { return this.prenom; }
    public String getDateNaissance() { return this.dateNaissance; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getIdentifiant()
        { return this.getNom() + "#" + this.getPrenom() + "#" + this.getDateNaissance(); }
}