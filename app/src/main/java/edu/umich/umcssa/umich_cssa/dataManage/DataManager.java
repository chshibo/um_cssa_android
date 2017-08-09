package edu.umich.umcssa.umich_cssa.dataManage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by chshibo on 8/9/17.
 * An standard interface for app to manage data
 * Single instance class
 * Call getInstance() to get an instance
 * @author Shibo Chen
 */
public class DataManager {

    private static class Holder{
        private static final DataManager INSTANCE=new DataManager();
    }

    private DataManager(){}

    public static final DataManager getInstance(){
        return Holder.INSTANCE;
    }

    /**
     *Reads in files and returns it as string
     * @param fileName the absolute path of the file
     * @return data in file as string
     */
    private String readFile(String fileName){
        String string=new String();
        File file=new File(fileName);
        Scanner scanner=null;
        try{
            scanner=new Scanner(file);
            string=scanner.useDelimiter("\\A").next();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            scanner.close();
        }
        return string;
    }


    /**
     * Writes data to the file
     * @param fileName absolute address of the file
     * @param data data
     */
    private void writeFile(String fileName, String data){
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;
        try{
            fileWriter=new FileWriter(fileName);
            bufferedWriter=new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets jsonObject from file
     * @param fileName absolute address of file
     * @return Parsed jsonObject
     */
    public JSONObject getJson(String fileName){
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(readFile(fileName));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
