package com.example.happyhunt;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    static String DBNAME = "HappyHunt.dp";
    static int VERSION = 1;
    static String FAVORITE_TABLE_NAME = "Favorite";
    static String COL1 = "id";
    static String COL2 = "placeName";
    static String COL3 = "placeAddres";
    static String COL4 = "type";

    static final String CREATE_FAVORITE_TABLE = "create table " + FAVORITE_TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, "
            + COL3 + " TEXT, "  + COL4 + " TEXT); ";
    static final String DROP_FAVORITE_TABLE = "DROP TABLE IF EXISTS " + FAVORITE_TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_FAVORITE_TABLE);
        onCreate(db);
    }

    public boolean InsertFavorite(Favorite objFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, objFavorite.getPlaceName());
        cv.put(COL3, objFavorite.getPlaceAddress());
        cv.put(COL4, objFavorite.getType());
        long result = db.insert(FAVORITE_TABLE_NAME, null, cv);
        return (result != -1);
    }

    public Integer DeleteFavorite(Favorite objFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "placeName=?";
        String[] whereArgs = new String[]{objFavorite.getPlaceName()};
        return db.delete(FAVORITE_TABLE_NAME, whereClause, whereArgs);
    }
}
