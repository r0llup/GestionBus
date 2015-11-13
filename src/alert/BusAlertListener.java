/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alert;

import java.util.EventListener;

/**
 *
 * @author qlecler
 * @author oli
 */

public interface BusAlertListener extends EventListener
{
    public void NewBusAlertReceived(BusAlertEvent e);
}