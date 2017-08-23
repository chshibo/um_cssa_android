package edu.umich.umcssa.umich_cssa.schedule;

import java.util.ArrayList;

public class Course{
    class Time{
        private String day;
        private String span;
        public Time(String day, String time){
            this.day=day;
            this.span =time;
        }
        public Time(){}

        public void setSpan(String span) {
            this.span = span;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getSpan() {
            return span;
        }

        public String getDay() {
            return day;
        }
    }
    private String courseName;
    private ArrayList<Time> time;
    private String location;
    private String secion;
    public Course(){
        time=new ArrayList<Time>();
    }
    public Course(String courseName,ArrayList<String> day,ArrayList<String> span,String location,String secion){
        this.courseName =courseName;
        this.time=new ArrayList<Time>();
        this.location=location;
        this.secion=secion;
        for (int i=0;i<day.size();++i){
            this.time.add(new Time(day.get(i),span.get(i)));
        }
    }
    public String  getSecion() {
        return secion;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Time> getTime() {
        return time;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSecion(String secion) {
        this.secion = secion;
    }

    public void setTime(ArrayList<Time> time) {
        this.time = time;
    }

    public void setTime(ArrayList<String> day,ArrayList<String> span){
        this.time=new ArrayList<Time>();
        for (int i = 0; i < day.size(); i++) {
            time.add(new Time(day.get(i),span.get(i)));
        }
    }

    public void addTime(String day, String span){
        this.time.add(new Time(day,span));
    }

    @Override
    public String toString() {
        String returnVal= getCourseName()+" "+getSecion()+" ";
        for (int i = 0; i <time.size() ; i++) {
            returnVal+=time.get(i).getDay()+" "+time.get(i).getSpan()+" ";
        }
        return returnVal+getLocation();
    }
}