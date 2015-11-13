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

public class MalFormedIdentifierException extends Exception
{
     public MalFormedIdentifierException()
     {
        super("Identifiant mal formé : ne contient pas que des lettres.");
     }
}