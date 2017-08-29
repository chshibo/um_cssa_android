package edu.umich.umcssa.umich_cssa;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import edu.umich.umcssa.umich_cssa.dataManage.DataManager;
import edu.umich.umcssa.umich_cssa.dataManage.FeedItemsContract;

/**
 * Created by chshibo on 8/23/17.
 */

public class ResourceGetter extends AsyncTask<ArrayList<String>,Integer,Boolean>{
    private MainActivity mainActivity;
    private static URL url;
    public ResourceGetter(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        try {
            this.url=new URL("http://138.68.19.91:80/requestFiles");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Boolean doInBackground(ArrayList<String>... arrayLists) {
        HttpURLConnection httpURLConnection=null;
        InputStream stream=null;
        try {
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestProperty("mode","article");
            JSONArray jsonArray=new JSONArray();
            for (int i=0;i<arrayLists[0].size();++i){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("idx",arrayLists[0].get(i));
                jsonArray.put(i,jsonObject.toString());
            }
            JSONObject object=new JSONObject();
            object.put("items",jsonArray);
            httpURLConnection.setRequestProperty("items",object.toString());
            httpURLConnection.connect();
            int requestCode=httpURLConnection.getResponseCode();
            JSONObject theContext=null;
            JSONArray items =null;
            if(requestCode== HttpURLConnection.HTTP_OK){
                stream=httpURLConnection.getInputStream();
                DataManager dataManager=new DataManager(mainActivity);
                String itemsDetail=dataManager.readStream(stream);
                theContext=new JSONObject(itemsDetail);
                items=theContext.getJSONArray("items");
            }
                //TODO
            Log.d("database",items.length()+"");


            for (int i = 0; i <items.length() ; i++) {
                if(saveJsonObjT(items.getJSONObject(i)))
                    saveItemInfoIntoDB(items.getJSONObject(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Boolean(true);
    }

    private Boolean saveJsonObjT(JSONObject jsonObject){
        DataManager dataManager=new DataManager(mainActivity);
        String fileName=null;
        try {
            fileName=jsonObject.getString(FeedItemsContract.FeedEntry.COLUMN_INDEX)+".json";
            dataManager.writeJson(mainActivity.getApplicationContext(),
                    jsonObject,fileName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean saveItemInfoIntoDB(JSONObject jsonObj){
        SQLiteDatabase db=this.mainActivity.getDBHelper().getWritableDatabase();
        ContentValues values=new ContentValues();
        try {
            values.put(FeedItemsContract.FeedEntry.COLUMN_TYPE,
                    jsonObj.getString(FeedItemsContract.FeedEntry.COLUMN_TYPE));
            values.put(FeedItemsContract.FeedEntry.COLUMN_TITLE,
                    jsonObj.getString(FeedItemsContract.FeedEntry.COLUMN_TITLE));
            values.put(FeedItemsContract.FeedEntry.COLUMN_AUTHOR,
                    jsonObj.getString(FeedItemsContract.FeedEntry.COLUMN_AUTHOR));
            values.put(FeedItemsContract.FeedEntry.COLUMN_TYPE,
                    jsonObj.getString(FeedItemsContract.FeedEntry.COLUMN_TYPE));
            values.put(FeedItemsContract.FeedEntry.COLUMN_TIME,
                    jsonObj.getInt(FeedItemsContract.FeedEntry.COLUMN_TIME));
            values.put(FeedItemsContract.FeedEntry.COLUMN_INDEX,
                    jsonObj.getInt(FeedItemsContract.FeedEntry.COLUMN_INDEX));

            db.insert(FeedItemsContract.FeedEntry.TABLE_NAME,null,values);

            //TODO
            Log.d("database",jsonObj.getString(FeedItemsContract.FeedEntry.COLUMN_TYPE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        this.mainActivity.asyncTaskFinished();
    }
}
