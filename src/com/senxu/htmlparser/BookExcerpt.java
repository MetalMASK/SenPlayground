/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senxu.htmlparser;

/**
 *
 * @author sxu
 */
public class BookExcerpt {
    private String author;
    private String title;
    private String imgurl;
    private String amazonID;
    private String excerpt;
    private String referralURL;
    
    public BookExcerpt(){
        author="";
        title="";
        imgurl="";
        amazonID="";
        excerpt="";
        referralURL="";
    }
    public BookExcerpt(String auth, String tit, String imgu, String amazID, String exrpt){
        author=auth;
        title=tit;
        imgurl=imgu;
        amazonID=amazID;
        excerpt=exrpt;
        referralURL="http://www.amazon.com/gp/product/"+amazonID+"/ref=as_li_tf_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN="+amazonID+"&linkCode=as2&tag=sesb05-20";
    }
    //getters
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getIMGURL(){
        return imgurl;
    }
    public String getExcerpt(){
        return excerpt;
    }
    public String getAmazonID(){
        return amazonID;
    }
    public String getReferralURL(){
        return referralURL;
    }
    //setters
    public void setAuthor(String s){
        author=s;
    }
    public void setTitle(String s){
        title=s;
    }
    public void setIMGURL(String s){
        imgurl=s;
    }
    public void setExcerpt(String s){
        excerpt=s;
    }
    public void setAmazonID(String s){
        amazonID=s;
        referralURL="http://www.amazon.com/gp/product/"+amazonID+"/ref=as_li_tf_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN="+amazonID+"&linkCode=as2&tag=sesb05-20";
    }
    
    
    public boolean isValid(){
        if (amazonID.isEmpty()) {
            return false;
        }
        if (referralURL.isEmpty()) {
            return false;
        }
        return true;
    }
    @Override
    public String toString(){
        if (this.isValid()){
            return ("author: "+author+"; title: "+title+"; amazonID: "+amazonID+"; imgurl: "+imgurl+"; excerpt: "+excerpt+"; referralURL:"+referralURL);
        }else{
            return "invalid record";
        }
        
    }
    
    
}
