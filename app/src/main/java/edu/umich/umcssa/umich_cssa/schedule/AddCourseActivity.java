package edu.umich.umcssa.umich_cssa.schedule;


import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import edu.umich.umcssa.umich_cssa.OnAsyncFinishListener;
import edu.umich.umcssa.umich_cssa.R;

public class AddCourseActivity extends AppCompatActivity implements OnAsyncFinishListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }

    public void btnAddClicked(View view) {
        EditText editText_catalog = (EditText) findViewById(R.id.editText_course_catalog);
        EditText editText_nbr = (EditText) findViewById(R.id.editText_nbr);

        ArrayList<Course> courses = new ArrayList<>();
        try {
            courses=new CourseCatcher(this).execute(editText_catalog.getText().toString().toUpperCase()
            +editText_nbr.getText().toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public boolean permissionCheck(){
        int InternetPermissionCheck=
                ContextCompat.checkSelfPermission(this,android.Manifest.permission.INTERNET);
        int InternetStatusPermissionCheck=
                ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_NETWORK_STATE);
        return InternetPermissionCheck== PackageManager.PERMISSION_GRANTED &&
                InternetStatusPermissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void AsyncTaskFinished() {

    }
}