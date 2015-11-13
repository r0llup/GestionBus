/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author qlecler
 * @author oli
 */

public class StringChecker
{
	private Boolean isValid;

	public StringChecker(String stringToCheck)
	{
		this.isValid = checkPortNumber(stringToCheck);
	}

	public Boolean getIsValid() { return this.isValid; }

	public Boolean checkPortNumber(String stringToCheck)
	{
		int portNumber;
		try
		{
			portNumber = Integer.parseInt(stringToCheck);
			if((portNumber > 0) && (portNumber < 65536))
			{
				return true;
			}
			else
			{
				System.out.println("Le port doit etre comprise entre 1 et 65535");
				return false;
			}
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Le port n'est pas valide");
			return false;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Port 31337 : " + new StringChecker("31337").getIsValid());
	}
}