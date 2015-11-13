/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qlecler
 * @author oli
 */

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("°°° Test de la classe Dimensions3D °°°");
            Dimensions3D dimensions3d = new Dimensions3D(200, 100, 50);
            System.out.println("Longueur : " + dimensions3d.getLongueur());
            System.out.println("Largeur : " + dimensions3d.getLargeur());
            System.out.println("Hauteur : " + dimensions3d.getHauteur());
            System.out.println("Unite : " + dimensions3d.getUnite());
            Dimensions3D dimensions3d1 = new Dimensions3D(dimensions3d);
            dimensions3d1.setLongueur(222);
            System.out.println("Longueur : " + dimensions3d1.getLongueur());
            System.out.println("Largeur : " + dimensions3d1.getLargeur());
            System.out.println("Hauteur : " + dimensions3d1.getHauteur());
            System.out.println("Unite : " + dimensions3d1.getUnite());
        }
        catch(data.exceptions.IncoherentDimensionsException N)
        {
            System.out.println("Exception : " + N.getMessage());
        }
        /**********************************************************************/
        System.out.println("\n°°° Test de la classe Bus °°°");
        Bus bus = new Bus(150, false, 80, true, true, "Gare routière");
        System.out.println("Numéro d'identification : " + bus.getNumId());
        System.out.println("En panne : " + bus.getEnPanne());
        System.out.println("Maximum de passagers : " + bus.getMaxPassager());
        System.out.println("Est articulé : " + bus.getEstArticule());
        System.out.println("En service : " + bus.getEnService());
        System.out.println("Dépot : " + bus.getDepot());
        Bus bus2 = new Bus(bus);
        bus2.setNumId(155);
        System.out.println("Numéro d'identification : " + bus2.getNumId());
        System.out.println("En panne : " + bus2.getEnPanne());
        System.out.println("Maximum de passagers : " + bus2.getMaxPassager());
        System.out.println("Est articulé : " + bus2.getEstArticule());
        System.out.println("En service : " + bus2.getEnService());
        System.out.println("Dépot : " + bus2.getDepot());
        /**********************************************************************/
        System.out.println("\n°°° Test de la classe Conducteur °°°");
        Conducteur conducteur = new Conducteur("Lecler", "Quentin",
                "31/07/1990", 1990073148, bus);
        System.out.println("Nom : " + conducteur.getNom());
        System.out.println("Prénom : " + conducteur.getPrenom());
        System.out.println("Date de naissance : " +
                conducteur.getDateNaissance());
        System.out.println("Matricule : " + conducteur.getMatricule());
        System.out.println("Numéro d'identification : " +
                conducteur.getBus().getNumId());
        System.out.println("En panne : " + conducteur.getBus().getEnPanne());
        System.out.println("Maximum de passagers : " +
                conducteur.getBus().getMaxPassager());
        System.out.println("Est articulé : " +
                conducteur.getBus().getEstArticule());
        System.out.println("En service : " +
                conducteur.getBus().getEnService());
        System.out.println("Dépot : " +
                conducteur.getBus().getDepot());
        Conducteur conducteur1 = new Conducteur(conducteur);
        conducteur1.setNom("Mich");
        conducteur1.setPrenom("Mich");
        System.out.println("Nom : " + conducteur1.getNom());
        System.out.println("Prénom : " + conducteur1.getPrenom());
        System.out.println("Date de naissance : " +
                conducteur1.getDateNaissance());
        System.out.println("Matricule : " + conducteur1.getMatricule());
        System.out.println("Numéro d'identification : " +
                conducteur1.getBus().getNumId());
        System.out.println("En panne : " + conducteur1.getBus().getEnPanne());
        System.out.println("Maximum de passagers : " +
                conducteur1.getBus().getMaxPassager());
        System.out.println("Est articulé : " +
                conducteur1.getBus().getEstArticule());
        System.out.println("En service : " +
                conducteur1.getBus().getEnService());
        System.out.println("Dépot : " + conducteur1.getBus().getDepot());
        /**********************************************************************/
        System.out.println("\n°°° Test de la classe Ligne °°°");
        Segment segment1 = new Segment("place kuborn", "rue ferrer", 15);
        Segment segment2 = new Segment("gare routière", "rue de la barge", 20);
        List<Segment> listeSegment = new ArrayList<Segment>();
        listeSegment.add(segment1);
        listeSegment.add(segment2);
        Ligne ligne = new Ligne(2, "Boncelle", listeSegment);
        System.out.println("Numéro : " + ligne.getNumero());
        System.out.println("Nom : " + ligne.getNom());
        for(Segment s:ligne.getListeSegment())
        {
            System.out.println("Arrêt 1 : " + s.getArret1());
            System.out.println("Arrêt 2 : " + s.getArret2());
            System.out.println("Durée : " + s.getDuree());
        }
        ligne.getListeSegment().add(new Segment("rue des augustins",
                "avenue de la république", 30));
        for(Segment s:ligne.getListeSegment())
        {
            System.out.println("Arrêt 1 : " + s.getArret1());
            System.out.println("Arrêt 2 : " + s.getArret2());
            System.out.println("Durée : " + s.getDuree());
        }
        /**********************************************************************/
        System.out.println("\n°°° Test de la classe Horaire °°°");
        LigneHoraire ligneHoraire1 =
                new LigneHoraire(bus, conducteur, ligne, "12/04/2005:15h40");
        LigneHoraire ligneHoraire2 =
                new LigneHoraire(bus2, conducteur1, ligne, "15/04/2005:16h30");
        List<LigneHoraire> listeLigneHoraire = new ArrayList<LigneHoraire>();
        listeLigneHoraire.add(ligneHoraire1);
        listeLigneHoraire.add(ligneHoraire2);
        Horaire horaire1 = new Horaire(listeLigneHoraire);
        for(LigneHoraire l:horaire1.getListeLigneHoraire())
        {
            System.out.println("Numéro d'identification : " +
                    l.getBus().getNumId());
            System.out.println("En panne : " + l.getBus().getEnPanne());
            System.out.println("Maximum de passagers : " +
                    l.getBus().getMaxPassager());
            System.out.println("Est articulé : " + l.getBus().getEstArticule());
            System.out.println("En service : " + l.getBus().getEnService());
            System.out.println("Dépot : " + l.getBus().getDepot());
            System.out.println("Nom : " + l.getConducteur().getNom());
            System.out.println("Prénom : " + l.getConducteur().getPrenom());
            System.out.println("Date de naissance : " +
                    l.getConducteur().getDateNaissance());
            System.out.println("Matricule : " +
                    l.getConducteur().getMatricule());
            System.out.println("Numéro d'identification : " +
                    l.getConducteur().getBus().getNumId());
            System.out.println("En panne : " +
                    l.getConducteur().getBus().getEnPanne());
            System.out.println("Maximum de passagers : " +
                    l.getConducteur().getBus().getMaxPassager());
            System.out.println("Est articulé : " +
                    l.getConducteur().getBus().getEstArticule());
            System.out.println("En service : " +
                    l.getConducteur().getBus().getEnService());
            System.out.println("Dépot : " +
                    l.getConducteur().getBus().getDepot());
            System.out.println("Numéro : " +
                    l.getLigne().getNumero());
            System.out.println("Nom : " +
                    l.getLigne().getNom());
            for(Segment s:l.getLigne().getListeSegment())
            {
                System.out.println("Arrêt 1 : " + s.getArret1());
                System.out.println("Arrêt 2 : " + s.getArret2());
                System.out.println("Durée : " + s.getDuree());
            }
            System.out.println("Date-heure de départ : " +
                    l.getDateHeureDepart());
        }
    }
}