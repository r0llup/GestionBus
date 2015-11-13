/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alert;

import java.util.EventObject;

/**
 *
 * @author qlecler
 * @author oli
 */

public final class BusAlertEvent extends EventObject
{
    private int typeIncident;
    private String ligneConcernee;

    public BusAlertEvent(Object source, int typeIncident)
    {
        super(source);
        this.setTypeIncident(typeIncident);
        this.setLigneConcernee(((BusAlertGeneratorBean) source).getLigneConcernee());
    }

    public int getTypeIncident() { return this.typeIncident; }
    public void setTypeIncident(int typeIncident) { this.typeIncident = typeIncident; }
    public String getLigneConcernee() { return this.ligneConcernee; }
    public void setLigneConcernee(String ligneConcernee) { this.ligneConcernee = ligneConcernee; }
}