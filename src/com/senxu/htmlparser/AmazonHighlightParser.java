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
public class AmazonHighlightParser {
    //http://www.amazon.com/gp/product/0805096663/ref=as_li_tf_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=0805096663&linkCode=as2&tag=sesb05-20
    private static ArrayList<BookExcerpt> HighlightParseFrom(int start){
        ArrayList<BookExcerpt> ret=new ArrayList<>();
        ArrayList<String> amazonID=new ArrayList<>();
        ArrayList<String> title=new ArrayList<>();
        ArrayList<String> excerpt=new ArrayList<>();
        ArrayList<String> imgurl=new ArrayList<>();
        ArrayList<String> author=new ArrayList<>();
        try {
            
            Source source = new Source(new URL("https://kindle.amazon.com/most_popular/highlights_all_time/"+start)); 
            List<Element> linkElements = source.getAllElements(HTMLElementName.A); 
            for (Element linkElement : linkElements) { 
                    String href = linkElement.getAttributeValue("href");
                    
                    if (href != null && (href.startsWith("/work/"))) { 
                            String titleStr=linkElement.getContent().getTextExtractor().toString();
                            if (!titleStr.isEmpty()){
                                if (href.split("/").length<2){//if parsing error: trying to parse B000FCJZ3G from /work/secrets-millionaire-mind-ebook/B000AZR2NI/B000FCJZ3G
                                    amazonID.add(href);
                                }else{
                                    amazonID.add(href.split("/")[href.split("/").length-1]);
                                }
                                title.add(titleStr);
                            }
                    }
             
            }
        List<Element> imgElements =  source.getAllElements(HTMLElementName.IMG);
             for (Element imgElement:imgElements){
                 String imgStr=imgElement.getAttributeValue("src");
                 if (imgStr !=null){
                     imgurl.add(imgStr);
                 }
        }
        List<Element> excerptElements =  source.getAllElements(HTMLElementName.SPAN);
             for (Element excerptElement:excerptElements){
                 String excerptStr=excerptElement.getAttributeValue("class");
                 if (!excerptStr.isEmpty()){
                     switch (excerptStr) {
                         case "author":
                             author.add(excerptElement.getContent().getTextExtractor().toString());
                             break;
                         case "highlight":
                             excerpt.add(excerptElement.getContent().getTextExtractor().toString());
                             break;
                     }
                 }
        }     
                
        } catch (IOException ex) {
            Logger.getLogger(JerichoParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i=0;i<author.size();i++){
            BookExcerpt be=new BookExcerpt();
            be.setAmazonID(amazonID.get(i));
            be.setAuthor(author.get(i));
            be.setExcerpt(excerpt.get(i));
            be.setIMGURL(imgurl.get(i));
            be.setTitle(title.get(i));
            System.out.println(be.toString());
            ret.add(be);
        }
        return ret;
    }
    
    public static void main(String[] args){
        ArrayList<BookExcerpt> be=new ArrayList<>();
        be = HighlightParseFrom(9976);
        
    }
    
}
