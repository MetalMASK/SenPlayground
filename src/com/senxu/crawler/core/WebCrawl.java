/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senxu.crawler.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author sxu
 */
public class WebCrawl {
    
    
    public static boolean CheckFile(File f){
        if (f.exists()) return true;
        else{
            try {
                f.createNewFile();
                return true;
            } catch (IOException ex) {
                Logger.getLogger(WebCrawl.class.getName()).log(Level.SEVERE, null, ex);
                return false;
                
            }
        }
    }
    
    public static void main(String[] args){

            File tmpfile=new File("test.html");
            if (!CheckFile(tmpfile)) {
                System.out.println("File Error: "+tmpfile.toString());
                Logger.getLogger(WebCrawl.class.getName()).log(Level.SEVERE, null, "file cannot be created");
            }
          try {          
                URL u = new URL("http://www.6pm.com/mens-sneakers-athletic-shoes~hJ#!/men-shoes/CK_XAXoI0wTTBNME0wSCAQiNFo8WjhaQFsABAuICAxgBDw.zso?s=percentOff/desc");
                SaveURL2File(u, tmpfile);
        } catch (Exception ex) {
            Logger.getLogger(WebCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void SaveURL2File(URL u, File tmpfile) {
        try {
            Document doc = Jsoup.connect(u.toString()).get();
            BufferedWriter bw=new BufferedWriter(new FileWriter(tmpfile));
            bw.append(doc.html());
            bw.flush();
            bw.close();
        } catch (Exception ex) {
            Logger.getLogger(WebCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
