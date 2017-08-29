package edu.umich.umcssa.umich_cssa.dataManage;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import edu.umich.umcssa.umich_cssa.MainActivity;
//XXX:content provider methods
/**
 * Created by chshibo on 8/9/17.
 * An standard interface for app to manage data
 * Single instance class
 * Call getInstance() to get an instance
 * @author Shibo Chen
 */
public class DataManager{
    Activity activity;
    public DataManager(Activity activity) {
        this.activity=activity;
    }


    /**
     *Reads in files and returns it as string
     * @param fileName the absolute path of the file
     * @return data in file as string
     */
    private String readFile(String fileName) throws FileNotFoundException{
        StringBuffer buffer=new StringBuffer();
        try{
            FileInputStream fin = activity.openFileInput(fileName);
            Reader reader=new InputStreamReader(fin,"UTF-8");
            char[] bufferedStr=new char[1];
            while (reader.read(bufferedStr)!=-1)
                buffer.append(bufferedStr);
            fin.close();
            reader.close();
        }catch (FileNotFoundException e){
            throw e;
        }catch (IOException e) {
                e.printStackTrace();
        }
        return buffer.toString();
    }


    /**
     * Writes data to the file
     * @param fileName absolute address of the file
     * @param data data
     */
    private void     writeFile(String fileName, String data){
        try{
            FileOutputStream fout =activity.openFileOutput(fileName, activity.MODE_PRIVATE);
            byte [] bytes = data.getBytes();
            fout.write(bytes);
            fout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Gets jsonObject from file
     * @param fileName address of file
     * @param context context of the app
     * @return Parsed jsonObject
     */
    public JSONObject getJson(Context context,String fileName) throws FileNotFoundException, JSONException{
        JSONObject jsonObject=null;
        try {
            String string=readFile(fileName);
            jsonObject=new JSONObject(readFile(fileName));
        } catch (JSONException e) {
            throw e;
        } catch (FileNotFoundException e){
            throw e;
        }
        return jsonObject;
    }

    /**
     * Writes jsonObj to file
     * @param context context of the file
     * @param jsonObject the jsonobj
     * @param fileName the fileName
     */
    public void writeJson(Context context,JSONObject jsonObject, String fileName){
        writeFile(fileName,jsonObject.toString());
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    public String readStream(InputStream stream)
            throws IOException {
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[1];
        StringBuffer buffer = new StringBuffer();
        while (reader.read(rawBuffer) != -1) {
            buffer.append(rawBuffer);
        }
        return buffer.toString();
    }
}
