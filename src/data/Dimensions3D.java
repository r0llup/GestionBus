/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import data.exceptions.IncoherentDimensionsException;

/**
 *
 * @author qlecler
 * @author oli
 */

public final class Dimensions3D implements Dimensions
{
    private float longueur;
    private float largeur;
    private float hauteur;

    public Dimensions3D(float longueur, float largeur, float hauteur)
        throws IncoherentDimensionsException
    {
        this.setLongueur(longueur);
        this.setLargeur(largeur);
        this.setHauteur(hauteur);
        if(this.getLargeur() > this.getLongueur())
            throw new data.exceptions.IncoherentDimensionsException();
    }

    public Dimensions3D(Dimensions3D dimensions3d)
    {
        this.setLongueur(dimensions3d.getLongueur());
        this.setLargeur(dimensions3d.getLargeur());
        this.setHauteur(dimensions3d.getHauteur());
    }

    public float getLongueur() { return this.longueur; }
    public float getLargeur() { return this.largeur; }
    public float getHauteur() { return this.hauteur; }
    public void setLongueur(float longueur) { this.longueur = longueur; }
    public void setLargeur(float largeur) { this.largeur = largeur; }
    public void setHauteur(float hauteur) { this.hauteur = hauteur; }
    public String getUnite()
        { return "Longueur : " + this.getLongueur() + " Largeur : " + this.getLargeur() +
            " Hauteur : " + this.getHauteur(); }
}