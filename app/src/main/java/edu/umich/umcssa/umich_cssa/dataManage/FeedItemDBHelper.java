package edu.umich.umcssa.umich_cssa.dataManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chshibo on 8/12/17.
 *
 * @author Shibo Chen
 */
// TODO: 8/12/17 FULLY IMPLEMENTS THIS CLASS
public class FeedItemDBHelper extends SQLiteOpenHelper {
    private static final int VERSION =1 ;
    private static final String DB_NAME = "FeedItem.db";
    public FeedItemDBHelper(Context context, String table_name,
                            SQLiteDatabase.CursorFactory factory, int version){
        super(context,table_name,factory,version);
    }

    public FeedItemDBHelper(Context context, String table_name, int version){
        super(context,table_name,null,version);
    }

    public FeedItemDBHelper(Context context){
        super(context,DB_NAME,null,VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(FeedItemsContract.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(FeedItemsContract.DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
