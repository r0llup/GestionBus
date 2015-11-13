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

public abstract class VehiculeTransportPassagers extends VehiculeTransport
    implements Serializable
{
    protected int maxPassager;

    public int getMaxPassager() { return this.maxPassager; }
    public void setMaxPassager(int maxPassager) { this.maxPassager = maxPassager; }
}