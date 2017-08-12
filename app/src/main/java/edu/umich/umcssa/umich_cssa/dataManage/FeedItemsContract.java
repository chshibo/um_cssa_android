package edu.umich.umcssa.umich_cssa.dataManage;

import android.provider.BaseColumns;

/** SQLLite class for cssa app
 * Created by chshibo on 8/12/17.
 * @author Shibo Chen
 */

public final class FeedItemsContract {
    private FeedItemsContract(){}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME="entry";
        public static final String COLUMN_TYPE="type";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_AUTHOR="author";
        public static final String COLUMN_TIME="time";
        public static final String COLUMN_STATUS="updated";
        public static final String COLUMN_LOCAL_LOC="local location";
        public static final String COLUMN_REMOTE_LOC="remote location";
    }
}
