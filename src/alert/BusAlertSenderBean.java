/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alert;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author qlecler
 * @author oli
 */

public class BusAlertSenderBean implements PropertyChangeListener
{
    public BusAlertSenderBean() { }

    public void propertyChange(PropertyChangeEvent evt)
    {
        System.out.println("Modified Propertie : " + evt.getPropertyName());
        System.out.println("Old Value : " + evt.getOldValue());
        System.out.println("New Value : " + evt.getNewValue());
    }
}