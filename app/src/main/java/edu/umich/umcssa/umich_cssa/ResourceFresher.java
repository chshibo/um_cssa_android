package edu.umich.umcssa.umich_cssa;

import android.os.AsyncTask;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chshibo on 8/23/17.
 */

public class ResourceFresher extends AsyncTask<Integer,Integer,ArrayList<String>>{
    private MainActivity mainActivity=null;
    private static URL url = null;
    public ResourceFresher(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        try {
            url=new URL("http://138.68.19.91:8080");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected ArrayList<String> doInBackground(Integer... integers) {
        ArrayList<String> strs=new ArrayList<>();
        HttpURLConnection connection=null;
        InputStream stream=null;
        try {
            connection=(HttpURLConnection)url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Mode","UPDATE");
            connection.setRequestProperty("Time",String.valueOf(integers[0]));
            connection.connect();
            int requestCode= connection.getResponseCode();
            if(requestCode== HttpURLConnection.HTTP_OK){
                stream=connection.getInputStream();
                String body=readStream(stream, Integer.MAX_VALUE);
                JSONObject jsonObj=new JSONObject(body);
                JSONArray arr=jsonObj.getJSONArray("Items");
                for (int i=0;i<arr.length();++i){
                    strs.add(arr.getString(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(stream!=null)
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(connection!=null)
                connection.disconnect();
        }
        return strs;
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

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
    }
}
