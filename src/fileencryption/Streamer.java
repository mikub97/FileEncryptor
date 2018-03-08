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
public abstract class Streamer extends Thread  {
    private FileInputStream instream;
    private FileOutputStream outstream;

    Streamer(File infile, File outfile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void Streamer(File infile,File outfile) throws FileNotFoundException {
        instream = new FileInputStream(infile);
        outstream = new FileOutputStream(outfile);                
} 
    
}
