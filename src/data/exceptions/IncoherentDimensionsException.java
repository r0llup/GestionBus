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

public class IncoherentDimensionsException extends Exception
{
    public IncoherentDimensionsException()
    {
        super("Dimensions incohérentes : Largeur > Longueur.");
    }
}