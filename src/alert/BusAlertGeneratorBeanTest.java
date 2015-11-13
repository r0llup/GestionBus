/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alert;

import java.beans.Beans;
import java.io.IOException;

/**
 *
 * @author qlecler
 * @author oli
 */

public class BusAlertGeneratorBeanTest implements BusAlertListener
{
    public static void main(String args[])
    {
        BusAlertGeneratorBeanTest bagbt = new BusAlertGeneratorBeanTest();
    }

    public BusAlertGeneratorBeanTest()
    {
        try
        {
            BusAlertGeneratorBean bagb = (BusAlertGeneratorBean) Beans.instantiate(null,
                "alert.BusAlertGeneratorBean");
            bagb.addBusAlertListener(this);
            bagb.init();
            bagb.run();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
            System.exit(1);
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(1);
        }
    }

    public void NewBusAlertReceived(BusAlertEvent e)
    {
        switch(e.getTypeIncident())
        {
            case BusConstants.INCIDENT_GREVE:
                System.out.println("Grève sur la ligne " + e.getLigneConcernee() + " !");
                break;
            case BusConstants.INCIDENT_INCIDENT:
                System.out.println("Incident sur la ligne " + e.getLigneConcernee() + " !");
                break;
            case BusConstants.INCIDENT_NEIGE:
                System.out.println("Neige sur la ligne " + e.getLigneConcernee() + " !");
                break;
            case BusConstants.INCIDENT_PANNE:
                System.out.println("Panne sur la ligne " + e.getLigneConcernee() + " !");
                break;
            case BusConstants.INCIDENT_VERGLAS:
                System.out.println("Verglas sur la ligne " + e.getLigneConcernee() + " !");
                break;
        }
    }
}