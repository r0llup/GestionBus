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

public class ValidPortCheckerTest
{
	private StringChecker sc;

	public ValidPortCheckerTest(String portNumber)
	{
		this.sc = new StringChecker(portNumber);
	}

	public static void main(String[] args)
	{
		System.out.println("Port 31337 : " + new ValidPortCheckerTest("31337").sc.getIsValid());
	}
}