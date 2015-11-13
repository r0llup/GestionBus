/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.exceptions;

/**
 *
 * @author qlecler
 */

public class NumIdException extends Exception
{
     public NumIdException()
     {
        super("Le numéro d'identification ne peut être inférieur ou égal à zéro.");
     }
}