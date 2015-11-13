package guis;
import java.awt.*;
import java.awt.event.*;
import utils.*;

public class DialLogin extends Dialog implements ActionListener, WindowListener
{
	private TextField nomPrenom, password, portCom;
	private Button okButton, cancelButton;

	public DialLogin(Frame parent, String titre, int longueur, int hauteur)
	{
		super(parent, titre);
		this.setSize(longueur, hauteur);
		this.addWindowListener(this);
		this.setLayout(new GridLayout(4, 2));
		this.add(new Label("Nom et prénom :"));
		this.nomPrenom = new TextField();
		this.add(this.nomPrenom);
		this.add(new Label("Password :"));
		this.password = new TextField();
		this.password.setEchoCharacter('*');
		this.add(this.password);
		this.add(new Label("Port de communication :"));
		this.portCom = new TextField();
		this.add(this.portCom);
		this.okButton = new Button("Ok");
		this.okButton.addActionListener(this);
		this.add(this.okButton);
		this.cancelButton = new Button("Cancel");
		this.cancelButton.addActionListener(this);
		this.add(this.cancelButton);
	}

	public TextField getNomPrenom() { return nomPrenom; }
	public TextField getPassword() { return password; }
	public TextField getPortCom() { return portCom; }
	public Button getOkButton() { return okButton; }
	public Button getCancelButton() { return cancelButton; }

	public static void main(String[] args)
	{
		Frame fl = new Frame();
		DialLogin dl = new DialLogin(fl, "Login pour application gestion bus", 400, 200);
		dl.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		String info;
		StringChecker sc;

		if((info = e.getActionCommand()) ==  "Ok")
		{
			System.out.println("Nom et Prénom : " + this.nomPrenom.getText());
			System.out.println("Password : " + this.password.getText());
			if(new StringChecker(this.portCom.getText()).getIsValid())
			{
				System.out.println("Port de communication : " + this.portCom.getText());
			}
		}

		this.setVisible(false);
	}

	public void windowClosing(WindowEvent e) { this.setVisible(false); }
	public void windowOpened(WindowEvent e) { }
	public void windowClosed(WindowEvent e) { }
	public void windowDeiconified(WindowEvent e) { }
	public void windowDeactivated(WindowEvent e) { }
	public void windowActivated(WindowEvent e) { }
	public void windowIconified(WindowEvent e) { }
}