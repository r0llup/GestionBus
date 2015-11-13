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

public class EtcException extends Exception
{
    public EtcException()
    {
        super("Exception soulevée.");
    }

    public EtcException(String s)
    {
        super(s);
    }
}