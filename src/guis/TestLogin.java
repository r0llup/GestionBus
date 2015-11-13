/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package guis;

import java.awt.*;
import java.awt.event.*;
import utils.*;

/**
 *
 * @author qlecler
 * @author oli
 */

public class TestLogin extends Frame implements ActionListener, WindowListener
{
	private Login dl;

	public TestLogin(String titre, int longueur, int largeur)
	{
		super(titre);
		this.setSize(longueur, largeur);
		this.addWindowListener(this);
		MenuBar mBar = new MenuBar();
		setMenuBar(mBar);
		Menu mWindow = new Menu("Fen�tres");
		mBar.add(mWindow);
		MenuItem mItemTest = new MenuItem("Test dialogue");
		mItemTest.addActionListener(this);
		mWindow.add(mItemTest);
		MenuItem mItemClose = new MenuItem("Fermer");
		mItemClose.addActionListener(this);
		mWindow.add(mItemClose);
	}

	public static void main(String[] args)
	{
		TestLogin tcdl = new TestLogin("Essai de fen�tre � menu", 400, 200);
		tcdl.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		String info;

		if((info = e.getActionCommand()).equals("Ok"))
		{
			if(new StringChecker(this.dl.getPortCom().getText()).getIsValid())
			{
				this.setTitle("Essai de fen�tre � menu " + this.dl.getNomPrenom().getText() + " (" + this.dl.getPortCom().getText() + ")");
			}
			else
			{
				this.setTitle("Essai de fen�tre � menu " + this.dl.getNomPrenom().getText());
			}
		}

		if((info = e.getActionCommand()).equals("Test dialogue"))
		{
			this.dl = new Login(this, "Login pour application gestion bus", 400, 200, true);
			this.dl.getOkButton().addActionListener(this);
			this.dl.getCancelButton().addActionListener(this);
			this.dl.setVisible(true);
		}

		if((info = e.getActionCommand()).equals("Fermer"))
		{
			System.exit(0);
		}
	}

	public void windowClosing(WindowEvent e) { System.exit(0); }
	public void windowOpened(WindowEvent e) { }
	public void windowClosed(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
	public void windowActivated(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
}