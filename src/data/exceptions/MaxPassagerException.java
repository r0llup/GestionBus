/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.exceptions;

/**
 *
 * @author qlecler
 */

public class MaxPassagerException extends Exception
{
     public MaxPassagerException()
     {
        super("Le nombre maximum de passagers doit etre positif.");
     }
}