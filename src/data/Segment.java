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

public final class Segment implements Serializable
{
    private String arret1;
    private String arret2;
    private int duree;

    public Segment(String arret1, String arret2, int duree)
    {
        this.setArret1(arret1);
        this.setArret2(arret2);
        this.setDuree(duree);
    }

    public Segment(Segment segment)
    {
        this.setArret1(segment.getArret1());
        this.setArret2(segment.getArret2());
        this.setDuree(segment.getDuree());
    }

    public String getArret1() { return this.arret1; }
    public String getArret2() { return this.arret2; }
    public int getDuree() { return this.duree; }
    public void setArret1(String arret1) { this.arret1 = arret1; }
    public void setArret2(String arret2) { this.arret2 = arret2; }
    public void setDuree(int duree) { this.duree = duree; }
}