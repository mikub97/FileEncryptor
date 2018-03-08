/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencryption;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mihasz
 */
public class Encryptor extends Thread {

    private FileInputStream instream;
    private FileOutputStream outstream;
    private int[] password;
    private int insideKey;
    private File in;
    private Gui gui;

    public Encryptor(File in, File out, int[] pass, Gui g) throws FileNotFoundException {
        instream = new FileInputStream(in);
        outstream = new FileOutputStream(out);
        password = pass;
        this.in = in;
        insideKey = 255;
        gui = g;

    }

    @Override
    public void run() {
        try {
            int counter = 0;
            while (instream.available() != 0) {
                outstream.write(encrypt(instream.read(), password[counter], counter));
                counter = (counter + 1) % password.length;

            }
            instream.close();
            outstream.close();
            if(gui.todelete())
                in.delete();
                
            gui.popup("Szyfrowanie zakończone");
        } catch (IOException ex) {
            Logger.getLogger(Encryptor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int encrypt(int file_byte, int pass_byte, int counter) {
        if (counter % 2 == 0) {
            return file_byte ^ pass_byte;
        } else {
            return ((file_byte ^ insideKey) ^ pass_byte);
        }
    }

    public int[] calculatePass(String pass) {
        int[] outcome = new int[pass.length()];
        for (int i = 0; i < pass.length(); i++) {
            outcome[i] = Integer.valueOf(pass.charAt(i));
        }


        return outcome;
    }

}
