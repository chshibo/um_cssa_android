package edu.umich.umcssa.umich_cssa.dataManage;

import android.provider.BaseColumns;

/** SQLLite class for cssa app
 * Created by chshibo on 8/12/17.
 * @author Shibo Chen
 */

public final class FeedItemsContract {
    private FeedItemsContract(){}
    public enum TYPES{RECENT_ACTIVITIES,NEWS,TICKET,SALES}
    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME="FeedEntry";
        public static final String COLUMN_TYPE="type";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_AUTHOR="author";
        public static final String COLUMN_TIME="time";
        public static final String COLUMN_STATUS="updated";
        public static final String COLUMN_LOCAL_LOC="local location";
        public static final String COLUMN_REMOTE_LOC="remote location";
    }

    public static final String CREATE_TABLE="CREATE TABLE "+FeedEntry.TABLE_NAME+
            "("+FeedEntry._ID+" INT PRIMARY KEY,"+FeedEntry.COLUMN_TYPE+" INT,"+
            FeedEntry.COLUMN_TITLE+" TEXT,"+FeedEntry.COLUMN_AUTHOR+" TEXT,"
            +FeedEntry.COLUMN_TIME+" INT,"+FeedEntry.COLUMN_STATUS+" BIT,"+
            FeedEntry.COLUMN_LOCAL_LOC+" TEXT,"+FeedEntry.COLUMN_REMOTE_LOC+" TEXT"+")";

    public static final String DELETE_TABLE="DROP TABLE IF EXISTS "+FeedEntry.TABLE_NAME;

}
