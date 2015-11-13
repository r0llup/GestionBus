/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package guis;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import utils.*;

/**
 *
 * @author qlecler
 * @author oli
 */

public class Login extends Dialog implements ActionListener, WindowListener
{
    private TextField nomPrenom, password, portCom;
    private Button okButton, cancelButton;
    private Frame parent;

    public Login(Frame parent, String titre, int longueur, int hauteur, Boolean modal)
    {
        super(parent, titre);
        this.parent = parent;
        this.setSize(longueur, hauteur);
        this.setModal(modal);
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

    public TextField getNomPrenom() { return this.nomPrenom; }
    public TextField getPassword() { return this.password; }
    public TextField getPortCom() { return this.portCom; }
    public Button getOkButton() { return this.okButton; }
    public Button getCancelButton() { return this.cancelButton; }

    public static void main(String[] args)
    {
        Frame fl = new Frame();
        Login dl = new Login(fl, "Login pour application gestion bus", 400, 200, true);
        dl.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        String info;
        if((info = e.getActionCommand()).equals("Ok"))
        {
            Properties propUser = new Properties();
            String filename = System.getProperty("file.separator") + "properties" +
                System.getProperty("file.separator") + "user.properties";
            try
            {
                propUser.load(ClassLoader.getSystemResourceAsStream(filename));
            }
            catch(FileNotFoundException ex)
            {
                System.out.println(ex);
                System.exit(1);
            }
            catch(IOException ex)
            {
                System.out.println(ex);
                System.exit(1);
            }
            System.out.println("Nom et Prénom : " + this.nomPrenom.getText());
            System.out.println("Password : " + this.password.getText());
            if(new StringChecker(this.portCom.getText()).getIsValid())
            {
                    System.out.println("Port de communication : " +
                            this.portCom.getText());
            }
            if(propUser.containsKey(this.nomPrenom.getText().toString()) &&
                propUser.containsValue(this.password.getText().toString()))
            {
                System.out.println("Connexion effectuée avec succès");
                this.setVisible(false);
            }
            else
            {
                System.out.println("Connexion non effectuée (identifiants invalides)");
            }
        }
        else
        {
            this.parent.setVisible(false);
        }
    }

    public void windowClosing(WindowEvent e) { this.parent.setVisible(false); }
    public void windowOpened(WindowEvent e) { }
    public void windowClosed(WindowEvent e) { }
    public void windowDeiconified(WindowEvent e) { }
    public void windowDeactivated(WindowEvent e) { }
    public void windowActivated(WindowEvent e) { }
    public void windowIconified(WindowEvent e) { }
}