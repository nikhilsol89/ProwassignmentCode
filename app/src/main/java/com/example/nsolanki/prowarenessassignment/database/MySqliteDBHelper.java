package com.example.nsolanki.prowarenessassignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class MySqliteDBHelper extends SQLiteOpenHelper {

    private static MySqliteDBHelper instance;

    private MySqliteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private MySqliteDBHelper(Context context) {
        super(context, DatabaseMetaData.DATABASE_NAME, null, DatabaseMetaData.DATABASE_VERSION);
    }

    public static synchronized MySqliteDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MySqliteDBHelper(context.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseMetaData.CONTACTS_LIST.TABLE_CREATING_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1 > i) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseMetaData.CONTACTS_LIST.TABLE_CREATING_QUERY);
        }
        onCreate(sqLiteDatabase);
    }
}
