package edu.umich.umcssa.umich_cssa.schedule;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by chshibo on 8/22/17.
 */

public interface OnAsyncFinishListener {
    public void AsyncTaskFinished(@NonNull ArrayList<Course> courses);
}
