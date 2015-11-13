/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data.exceptions;

/**
 *
 * @author qlecler
 * @author oli
 */

public class DureeNegativeException extends Exception
{
    public DureeNegativeException()
    {
        super("Durée entrée négative.");
    }
}