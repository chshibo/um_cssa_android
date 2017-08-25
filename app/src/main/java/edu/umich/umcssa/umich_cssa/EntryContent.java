package edu.umich.umcssa.umich_cssa;

import android.app.ListFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chshibo on 8/15/17.
 * @author Shibo Chen
 */

public class EntryContent {
    List<Item> ITEMS;

    EntryContent(List<Item> ITEMS){
        this.ITEMS=ITEMS;
    }
    EntryContent(){
        ITEMS=new ArrayList<Item>();
    }
    public void addItem(Item item){
        ITEMS.add(item);
        Collections.sort(ITEMS,new DateComparator());
    }

    public void addItem(String title, String author, int date, int index){
        addItem(new Item(title,author,date,index));
    }
    class Item{
        private String title;
        private String author;
        private int date;
        private int index;

        Item(String title,String author, int date, int index){
            this.title=title;
            this.author=author;
            this.date=date;
            this.index=index;
        }
        Item(){
            this.title=new String();
            this.author=new String();
            this.date=0;
            this.index=0;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setDate(int date) {
            this.date = date;
        }


        public void setTitle(String title) {
            this.title = title;
        }

        public int getDate() {
            return date;
        }

        public String getAuthor() {
            return author;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public String getTitle() {
            return title;
        }
    }
    class DateComparator implements Comparator<Item>{

        @Override
        public int compare(Item lhs, Item rhs) {
            if(lhs.date>rhs.date)
                return 0;
            else return 1;
        }
    }
}
