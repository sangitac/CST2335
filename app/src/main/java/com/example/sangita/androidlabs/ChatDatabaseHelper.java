package com.example.sangita.androidlabs;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends  SQLiteOpenHelper {
    protected static final String activity_name= "ChatDatabaseHelper";
    private static final int VERSION_NUM = 1
            ;
    public static final String DATABASE_NAME = "Chats.db";
    public static final String TABLE_NAME = "MyMessage";
    public static final String KEY_ID= "_id";
    public static final String KEY_MESSAGE = "message";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

 @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_MESSAGE_TABLE  = "CREATE TABLE " + TABLE_NAME + "(" +
                                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                        KEY_MESSAGE +" TEXT NOT NULL" +")";

       db.execSQL(CREATE_MESSAGE_TABLE);

       Log.i(activity_name, "Calling onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        Log.i(activity_name, "Calling onUpgrade, oldVersion=" + oldVersion+ " newVersion=" + newVersion);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        Log.i(activity_name, "Calling onDowngrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

}
