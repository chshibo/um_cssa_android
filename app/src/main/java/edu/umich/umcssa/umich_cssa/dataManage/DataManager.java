package edu.umich.umcssa.umich_cssa.dataManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
//XXX:content provider methods
/**
 * Created by chshibo on 8/9/17.
 * An standard interface for app to manage data
 * Single instance class
 * Call getInstance() to get an instance
 * @author Shibo Chen
 */
public class DataManager{

    private static class Holder{
        private static final DataManager INSTANCE=null;
    }

    private DataManager() {}

    public static final DataManager getInstance(){return Holder.INSTANCE;}

    /**
     *Reads in files and returns it as string
     * @param fileName the absolute path of the file
     * @param context the context
     * @return data in file as string
     */
    private String readFile(Context context,String fileName) throws FileNotFoundException{
        String string=new String();
        File file=new File(context.getFilesDir(),fileName);
        Scanner scanner=null;
        try{
            scanner=new Scanner(file);
            string=scanner.useDelimiter("\\A").next();
        }catch (FileNotFoundException e){
            throw e;
        }finally {
            scanner.close();
        }
        return string;
    }


    /**
     * Writes data to the file
     * @param context context of the file
     * @param fileName absolute address of the file
     * @param data data
     */
    private void writeFile(Context context,String fileName, String data){
        FileWriter fileWriter=null;
        BufferedWriter bufferedWriter=null;
        File file=null;
        try{
            file=new File(context.getFilesDir(),fileName);
            fileWriter=new FileWriter(file.getAbsolutePath());
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
     * @param fileName address of file
     * @param context context of the app
     * @return Parsed jsonObject
     */
    public JSONObject getJson(Context context,String fileName) throws FileNotFoundException, JSONException{
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(readFile(context,fileName));
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
        writeFile(context,fileName,jsonObject.toString());
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    public String readStream(InputStream stream, int maxReadSize)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        StringBuffer buffer = new StringBuffer();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }
}
