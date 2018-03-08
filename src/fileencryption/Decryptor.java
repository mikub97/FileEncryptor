/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileencryption;

import java.io.*;

/**
 *
 * @author mihasz
 */
public class Decryptor extends Thread {    
    private FileInputStream instream;
    private FileOutputStream outstream;

    public Decryptor(File in, File out) throws FileNotFoundException {
        instream = new FileInputStream(in);
        outstream = new FileOutputStream(out);  
    }
    
    @Override
    public void run() {
    }
    
}
