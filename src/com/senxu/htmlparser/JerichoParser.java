/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senxu.htmlparser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

/**
 *
 * @author sxu
 */
public class JerichoParser {
    
    public static void main(String[] args){
        try {
            Source source = new Source(new URL("https://kindle.amazon.com/most_popular/highlights_all_time/9976")); 
            List<Element> linkElements = source.getAllElements(HTMLElementName.A); 
            for (Element linkElement : linkElements) { 
                    String href = linkElement.getAttributeValue("href");
                    
                    if (href != null && (href.startsWith("/work/"))) { 
                            String titleStr=linkElement.getContent().getTextExtractor().toString();
                            if ((!titleStr.isEmpty())){
                                System.out.println(href); 
                                System.out.println(titleStr);
                            }
                    }
             
            }
        List<Element> imgElements =  source.getAllElements(HTMLElementName.IMG);
             for (Element imgElement:imgElements){
                 String imgStr=imgElement.getAttributeValue("src");
                 if (imgStr !=null){
                     System.out.println(imgStr);
                 }
        }
//        List<Element> titleElements =  source.getAllElements(HTMLElementName.A);
//             for (Element titleElement:titleElements){
//                 String titleStr=titleElement.getContent().getTextExtractor().toString();
//                 if (titleStr !=null){
//                     System.out.println(titleStr);
//                 }
//        }     
        } catch (IOException ex) {
            Logger.getLogger(JerichoParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
