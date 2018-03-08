package fileencryption;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mihasz
 */
public class PasswordDialog {
    private JFrame controllingFrame; //needed for dialogs
    private JPasswordField passwordField;
    private Controler controler;
    private JButton ok;

    
    public PasswordDialog(Controler c) {
        controllingFrame = new JFrame("Password");
        passwordField = new JPasswordField();
        JLabel label = new JLabel("Enter the password: ");
        label.setLabelFor(passwordField);
        ok = new JButton("OK");
        controler = c;
        controllingFrame.add(ok);
        controllingFrame.add(passwordField);
        controllingFrame.add(label);
        
        
    }
    
    public class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ok) {
                if (passwordField.getPassword().length > 0)
                    controler.setPassword(passwordField.getPassword());
            }
                
        }
    
    }
    
}
