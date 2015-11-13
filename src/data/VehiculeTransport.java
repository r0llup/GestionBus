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

public abstract class VehiculeTransport implements ObjetMateriel,
        EstIdentifiable, Serializable
{
    protected int numId;
    protected boolean enPanne;
    protected Dimensions3D dimensions;

    public int getNumId() { return this.numId; }
    public boolean getEnPanne() { return this.enPanne; }
    public String  getIdentifiant() { return String.valueOf(numId); }
    public Dimensions3D getDimensions() { return this.dimensions; }
    public void setNumId(int numId) { this.numId = numId; }
    public void setEnPanne(boolean enPanne) { this.enPanne = enPanne; }
    public void setDimensions(Dimensions3D dimensions) { this.dimensions = dimensions; }
}