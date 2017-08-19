package edu.umich.umcssa.umich_cssa.schedule;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * A singleton class to do the web course catcher
 * @author Shibo Chen
 */
public class CourseCatcher {
    private CourseCatcher(){}

    private static class Holder{
        private static CourseCatcher courseCatcher = new CourseCatcher();
    }

    public static final String TERM_2017_FALL="f_17_2160";
    public static final String INDEX_PAGE="http://www.lsa.umich.edu/cg/cg_detail.aspx?";
    public static final String ARG_CONTENT="content=";
    public static final String ARG_TERM="termArray=";



    public static CourseCatcher getInstance(){
        return Holder.courseCatcher;
    }

    public Document getUrlDocument(String url) throws IOException{
        Document doc=null;
        try{
            doc=Jsoup.parse(new URL(url),10000);
        }catch (IOException e){
            throw e;
        }
        return doc;
    }

    public ArrayList<Course> getCourses(String catalog,String index) throws IOException{
        ArrayList<Course> courses = new ArrayList<Course>();
        Document document=null;
        try {
            document=getUrlDocument(INDEX_PAGE+ARG_CONTENT+"2160"+
                    catalog.toUpperCase()+index+"001"+"&"+ARG_TERM+TERM_2017_FALL);
        } catch (IOException e) {
            throw e;
        }
        Elements coursesElts=document.getElementsByClass("clsschedulerow");
        for (Element elt: coursesElts) {
            Course course=new Course();
            course.setSecion(elt.select("span").first().text());
            course.setIndex(String.valueOf(index));
            course.setCatalog(catalog);
            course.setLocation(elt.getElementsByClass("loc_link").first().text());
            Elements days=elt.getElementsByClass("MPCol_Day");
            Elements spans=elt.getElementsByClass("MPCol_time");
            for (int i = 0; i <days.size() ; i++) {
                course.addTime(days.get(i).text(),spans.get(i).text());
            }
            courses.add(course);
        }
        return courses;
    }
}
