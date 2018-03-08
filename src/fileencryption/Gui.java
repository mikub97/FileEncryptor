package fileencryption;

import fileencryption.Gui.Listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mihasz
 */
public class Gui extends JFrame {

    private final JButton wczytaj;
    private final JPanel panel;
    private final JCheckBox delete;
    private final Gui gui;
    private final int MAXIMUM_NAME_LENGTH;
    private File file;
    private Controler controler;
    private final JLabel state;
    private final JButton szyfruj;

    public Gui() throws IOException {
        super("File Encryptor");
        szyfruj = new JButton("Szyfruj");
        wczytaj = new JButton("Wczytaj plik");
        delete = new JCheckBox("Usunąć plik?");

        gui = this;
        gui.setResizable(true);
        MAXIMUM_NAME_LENGTH = 15;
        controler = new Controler(gui);

        state = new JLabel("Nie wybrano pliku do szyfrowania");
        panel = new JPanel();
        panel.add(wczytaj);
        panel.add(state);
        panel.add(szyfruj);
        panel.add(delete);

        Listener listener = new Listener();
        wczytaj.addActionListener(listener);
        szyfruj.addActionListener(listener);

        this.add(panel);
        this.setSize(300, 120);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }

    public void popup(String message) {
        JFrame frame;
        frame = new JFrame("Info");
        JOptionPane.showMessageDialog(frame, message);
    }

    public boolean todelete() {
        return delete.isSelected();
    }

    public class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == wczytaj) {
                try {
                    file = Read();
                    if (file != null) {
                        if (file.getName().length() > MAXIMUM_NAME_LENGTH) {
                            state.setText("Plik do szyfrowania: " + file.getName().substring(0, MAXIMUM_NAME_LENGTH) + "...");
                        } else  {
                            state.setText("Plik do szyfrowania: " + file.getName());
                        } 
                    }
                    else {
                            state.setText("Nie wybrano pliku do szyfrowania");
                        }
                    }
                    catch (IOException ex) {
                    gui.popup("Bład we wczytywaniu pliku // IOException");
                }
                }
                if (e.getSource() == szyfruj) {
                    try {
                        if (file != null) {
                            controler.setInfile(file);
                            if (gui.askForPass() == 0) {
                                controler.encrypt();
                            }
                        } else {
                            gui.popup("Nie wybrano pliku do (od)szyfrowania");
                        }
                    } catch (IOException ex) {
                        gui.popup("Bład we wczytywaniu pliku // IOException");
                    }
                }
            }
        }

        public File Read() throws IOException {

            JFileChooser fileChooser = new JFileChooser();
            File f = new File(new File("./").getCanonicalPath());
            fileChooser.setCurrentDirectory(f);
            int result = fileChooser.showOpenDialog(null);
            if (result == fileChooser.APPROVE_OPTION) {
                f = fileChooser.getSelectedFile();
                return f;
            } else if (result == fileChooser.CANCEL_OPTION) {
                return null;
            } else {
                return null;
            }
        }

        public int askForPass() {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Enter a password:");
            JPasswordField pass = new JPasswordField(10);
            panel.add(label);
            panel.add(pass);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(null, panel, "Podaj klucz do (od)szyfrowania",
                    JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[1]);
            if (option == 0) // pressing OK button
            {
                char[] password = pass.getPassword();
                controler.setPassword(password);
                return 0;
            } else {
                return -1;
            }
        }

    }
