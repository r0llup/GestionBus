package guis;
import java.awt.*;
import java.awt.event.*;
import utils.*;

public class TestConsoleDialLogin extends Frame implements ActionListener, WindowListener
{
	private DialLogin dl;

	public TestConsoleDialLogin(String titre, int longueur, int largeur)
	{
		super(titre);
		this.setSize(longueur, largeur);
		this.addWindowListener(this);
		MenuBar mBar = new MenuBar();
		setMenuBar(mBar);
		Menu mWindow = new Menu("Fenêtres");
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
		TestConsoleDialLogin tcdl = new TestConsoleDialLogin("Essai de fenêtre à menu", 400, 200);
		tcdl.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		String info;

		if((info = e.getActionCommand()) ==  "Ok")
		{
			if(new StringChecker(this.dl.getPortCom().getText()).getIsValid())
			{
				this.setTitle("Essai de fenêtre à menu " + this.dl.getNomPrenom().getText() + " (" + this.dl.getPortCom().getText() + ")");
			}
			else
			{
				this.setTitle("Essai de fenêtre à menu " + this.dl.getNomPrenom().getText());
			}
		}

		if((info = e.getActionCommand()) == "Test dialogue")
		{
			this.dl = new DialLogin(this, "Login pour application gestion bus", 400, 200);
			this.dl.getOkButton().addActionListener(this);
			this.dl.getCancelButton().addActionListener(this);
			this.dl.setVisible(true);
		}

		if((info = e.getActionCommand()) == "Fermer")
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