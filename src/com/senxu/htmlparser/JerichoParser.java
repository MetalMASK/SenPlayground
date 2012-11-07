/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senxu.htmlparser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
        ArrayList<String> url=new ArrayList<>();
        ArrayList<String> title=new ArrayList<>();
        ArrayList<String> excerpt=new ArrayList<>();
        ArrayList<String> imgurl=new ArrayList<>();
        ArrayList<String> author=new ArrayList<>();
        
        try {
            
            Source source = new Source(new URL("https://kindle.amazon.com/most_popular/highlights_all_time/9976")); 
            List<Element> linkElements = source.getAllElements(HTMLElementName.A); 
            for (Element linkElement : linkElements) { 
                    String href = linkElement.getAttributeValue("href");
                    
                    if (href != null && (href.startsWith("/work/"))) { 
                            String titleStr=linkElement.getContent().getTextExtractor().toString();
                            if (!titleStr.isEmpty()){
//                                System.out.println(href); 
                                url.add(href);
//                                System.out.println(titleStr);
                                title.add(titleStr);
                            }
                    }
             
            }
        List<Element> imgElements =  source.getAllElements(HTMLElementName.IMG);
             for (Element imgElement:imgElements){
                 String imgStr=imgElement.getAttributeValue("src");
                 if (imgStr !=null){
//                     System.out.println(imgStr);
                     imgurl.add(imgStr);
                 }
        }
        List<Element> excerptElements =  source.getAllElements(HTMLElementName.SPAN);
             for (Element excerptElement:excerptElements){
                 String excerptStr=excerptElement.getAttributeValue("class");
                 if (!excerptStr.isEmpty()){
                     if (excerptStr.equals("author")){
//                         System.out.println(excerptElement.getContent().getTextExtractor().toString());
                         author.add(excerptElement.getContent().getTextExtractor().toString());
                     }else if (excerptStr.equals("highlight")){
//                         System.out.println(excerptElement.getContent().getTextExtractor().toString());
                         excerpt.add(excerptElement.getContent().getTextExtractor().toString());
                     }
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
        
//        System.out.println("Found: " +url.size()+ " urls");
//        System.out.println("Found: " +title.size()+ " titles");
//        System.out.println("Found: " +excerpt.size()+ " excerpts");
//        System.out.println("Found: " +imgurl.size()+ " imgurls");
//        System.out.println("Found: " +author.size()+ " authors");
        
        for (int i=0;i<url.size();i++){
            System.out.println("URL: " +url.get(i));
            System.out.println("title: " +title.get(i));
            System.out.println("excerpt: " +excerpt.get(i));
            System.out.println("imgurl: " +imgurl.get(i));
            System.out.println("author: " +author.get(i));
        
        }
    }
    
}
