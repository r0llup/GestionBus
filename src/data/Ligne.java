/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author qlecler
 * @author oli
 */

public final class Ligne implements Serializable
{
    private int numero;
    private String nom;
    private List<Segment> listeSegment;

    public Ligne(int numero, String nom, List<Segment> listeSegment)
    {
        this.setNumero(numero);
        this.setNom(nom);
        this.listeSegment = new ArrayList<Segment>();
        this.setListeSegment(listeSegment);
    }

    public Ligne(Ligne ligne)
    {
        this.setNumero(ligne.getNumero());
        this.setNom(ligne.getNom());
        this.listeSegment = new ArrayList<Segment>();
        this.setListeSegment(ligne.getListeSegment());
    }

    public int getNumero() { return this.numero; }
    public String getNom() { return this.nom; }
    public List<Segment> getListeSegment() { return this.listeSegment; }
    public void setNumero(int numero) { this.numero = numero; }
    public void setNom(String nom) { this.nom = nom; }
    public void setListeSegment(List<Segment> listeSegment)
    {
        if(!listeSegment.isEmpty())
            for(Segment s:listeSegment)
                this.listeSegment.add(s);
    }
}