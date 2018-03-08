/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencryption;

import java.io.*;
import javax.swing.JFileChooser;

/**
 *
 * @author mihasz
 */
public final class Controler {

    private File infile;
    private File outfile;
    private int [] password;
    private Encryptor cryptor;
    private Gui gui;

    public Controler( Gui g) throws FileNotFoundException, IOException {
        gui = g;
        infile=null;
        outfile=null;
    }

    public void encrypt() throws IOException {
        outfile = save();
        if (outfile.equals(infile)) {
            gui.popup("Plik do (od)szyfrowania musi być różny od pliku docelowego");
            outfile=null;
        }
        else if (outfile != null) {
            cryptor = new Encryptor(infile, outfile, password, gui);
            cryptor.start();
        }
        else 
            ;
    }

    public File save() throws FileNotFoundException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Zapisz zaszyfrowany plik");
        File f = new File(new File("./").getCanonicalPath());
        fileChooser.setSelectedFile(infile);
        

        int userSelection = fileChooser.showSaveDialog(null);
        File saveFile = null;
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            saveFile = fileChooser.getSelectedFile();
        }
         else if (userSelection == fileChooser.CANCEL_OPTION) {
            return null;
        }
        return saveFile;
    }
    public void setInfile(File f) {
        infile = f;
    }
    public void setPassword(char [] pass) {
        password = new int [pass.length];
        for (int i = 0 ; i< pass.length; i++) {
            password[i] = (int) pass[i];
        }
    }

}
