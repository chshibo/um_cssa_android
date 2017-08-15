package edu.umich.umcssa.umich_cssa.dataManage;

import android.provider.BaseColumns;

/**
 * Created by chshibo on 8/12/17.
 * @author Shibo Chen
 */

public final class CourseContract {
    private CourseContract(){}

    public static class Course implements BaseColumns{
        public static final String TABLE_NAME="Course";
        public static final String COLUMN_COURSE_INDEX="course_num";
        public static final String COLUMN_COURSE_NAME="name";
        public static final String COLUMN_START_TIME="start_time";
        public static final String COLUMN_END_TIME="end_time";
        public static final String COLUMN_LOC="location";
        public static final String COLUMN_PROFESSOR="professor";
    }
    public static final String CREATE_TABLE="CREATE TABLE "+Course.TABLE_NAME+"("+
            Course._ID+" INTEGER PRIMARY KEY,"+Course.COLUMN_COURSE_INDEX+" TEXT,"+
            Course.COLUMN_COURSE_NAME+" TEXT,"+Course.COLUMN_START_TIME+" INTEGER,"+
            Course.COLUMN_END_TIME+" INTEGER,"+Course.COLUMN_LOC+" TEXT,"+Course.COLUMN_PROFESSOR+
            " TEXT"+")";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS "+Course.TABLE_NAME;
}
