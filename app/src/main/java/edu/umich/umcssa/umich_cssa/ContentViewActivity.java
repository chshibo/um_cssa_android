package edu.umich.umcssa.umich_cssa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.umich.umcssa.umich_cssa.dataManage.DataManager;
import edu.umich.umcssa.umich_cssa.dataManage.DisplayJsonContract;

public class ContentViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_view);
        Bundle bundle=(Bundle)getIntent().getExtras();
        DataManager dataManager=new DataManager(this);
        try{
            JSONObject jsonObject=dataManager.getJson(this.getApplicationContext(),bundle.getString(MainActivity.ARGS_PATH));
            TextView text_Header=(TextView)findViewById(R.id.textView_header);
            TextView text_content=(TextView)findViewById(R.id.textView_content);
            text_Header.setText(jsonObject.getString(DisplayJsonContract.Elements.HEADER));
            SimpleDateFormat spf=new SimpleDateFormat("MMM/dd/YYYY HH/mm");
            Date date=new Date(jsonObject.getInt(DisplayJsonContract.Elements.TIME));
            String dateFormate=spf.format(date);
            text_content.setText(jsonObject.getString(DisplayJsonContract.Elements.AUTHOR)+"\n"
                                +dateFormate+"\n"+
                                jsonObject.getString(DisplayJsonContract.Elements.CONTENT));
        }catch (FileNotFoundException e){
            // TODO: 8/15/17 Handle this
            e.printStackTrace();
        }catch (JSONException e){
            // TODO: 8/15/17 Handle this
            e.printStackTrace();
        }
    }
}
