package edu.umich.umcssa.umich_cssa.schedule;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.umich.umcssa.umich_cssa.OnAsyncFinishListener;

/**
 * A singleton class to do the web course catcher
 * @author Shibo Chen
 */
public class CourseCatcher extends AsyncTask<String,Integer,ArrayList<Course>>{

    private OnAsyncFinishListener onAsyncFinishListener;

    public CourseCatcher(OnAsyncFinishListener onAsyncFinishListener){
        this.onAsyncFinishListener=onAsyncFinishListener;
    }
    @Override
    protected ArrayList<Course> doInBackground(String... strings) {
        ArrayList<Course> courses=new ArrayList<>();
        for (String str:strings
             ) {
            try {
                courses=getCourses(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return courses;
    }


    public static final String TERM_2017_FALL="f_17_2160";
    public static final String INDEX_PAGE="http://www.lsa.umich.edu/cg/cg_detail.aspx?";
    public static final String ARG_CONTENT="content=";
    public static final String ARG_TERM="termArray=";



    public Document getUrlDocument(String url) throws IOException{
        Document doc=null;
        try{
            doc=Jsoup.parse(new URL(url),10000);
        }catch (IOException e){
            throw e;
        }
        return doc;
    }

    /**
     * @param catalog_nbr needed to be uppercase
     * @return
     * @throws IOException
     */
    public ArrayList<Course> getCourses(String catalog_nbr) throws IOException{

        ArrayList<Course> courses = new ArrayList<Course>();
        Document document=null;
        try {
            document=getUrlDocument(INDEX_PAGE+ARG_CONTENT+"2160"+
                    catalog_nbr+"001"+"&"+ARG_TERM+TERM_2017_FALL);
        } catch (IOException e) {
            throw e;
        }
        Elements coursesElts=document.getElementsByClass("clsschedulerow");
        for (Element elt: coursesElts) {
            Course course=new Course();
            course.setSecion(elt.select("span").first().text());
            course.setCourseName(catalog_nbr);
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

    @Override
    protected void onPostExecute(ArrayList<Course> courses) {
        super.onPostExecute(courses);
    }
}
