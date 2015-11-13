package data;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author qlecler
 * @author oli
 */

public final class Horaire
{
    private List<LigneHoraire> listeLigneHoraire;

    public Horaire(List<LigneHoraire> listeLigneHoraire)
    {
        this.listeLigneHoraire = new ArrayList<LigneHoraire>();
        this.setListeLigneHoraire(listeLigneHoraire);
    }

    public Horaire(Horaire horaire)
    {
        this.listeLigneHoraire = new ArrayList<LigneHoraire>();
        this.setListeLigneHoraire(horaire.getListeLigneHoraire());
    }

    public List<LigneHoraire> getListeLigneHoraire() { return this.listeLigneHoraire; }
    public void setListeLigneHoraire(List<LigneHoraire> listeLigneHoraire)
    {
        if(!listeLigneHoraire.isEmpty())
            for(LigneHoraire l:listeLigneHoraire)
                this.listeLigneHoraire.add(l);
    }
}