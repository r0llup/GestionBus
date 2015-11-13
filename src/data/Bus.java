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

public final class Bus extends VehiculeTransportPassagers implements Serializable
{
    private boolean estArticule;
    private boolean enService;
    private String depot;

    public Bus(int numId, boolean enPanne, int maxPassager, boolean estArticule,
            boolean enService, String depot)
    {
        this.setNumId(numId);
        this.setEnPanne(enPanne);
        this.setMaxPassager(maxPassager);
        this.setEstArticule(estArticule);
        this.setEnService(enService);
        this.setDepot(depot);
    }

    public Bus(Bus bus)
    {
        this.setNumId(bus.getNumId());
        this.setEnPanne(bus.getEnPanne());
        this.setMaxPassager(bus.getMaxPassager());
        this.setEstArticule(bus.getEstArticule());
        this.setEnService(bus.getEnService());
        this.setDepot(bus.getDepot());
    }

    public boolean getEstArticule() { return this.estArticule; }
    public boolean getEnService() { return this.enService; }
    public String getDepot() { return this.depot; }
    public void setEstArticule(boolean estArticule) { this.estArticule = estArticule; }
    public void setEnService(boolean enService) { this.enService = enService; }
    public void setDepot(String depot) { this.depot = depot; }
}