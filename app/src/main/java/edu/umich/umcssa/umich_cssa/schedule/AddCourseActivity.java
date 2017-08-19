package edu.umich.umcssa.umich_cssa.schedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import edu.umich.umcssa.umich_cssa.R;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
    }

    public void btnAddClicked(View view){
        EditText editText_catalog=(EditText)findViewById(R.id.editText_course_catalog);
        EditText editText_nbr=(EditText)findViewById(R.id.editText_nbr);

        CourseCatcher courseCatcher=CourseCatcher.getInstance();
        ArrayList<Course> courses=new ArrayList<Course>();
        try {
            courses=courseCatcher.getCourses(
                    editText_catalog.getText().toString(),editText_nbr.getText().toString()
            );
        } catch (IOException e) {
            // TODO: 8/19/17 Hanlde exceptions
            e.printStackTrace();
        }
        //TODO
        TextView text=(TextView)findViewById(R.id.textView4);
        text.setText(courses.size());
    }
}
