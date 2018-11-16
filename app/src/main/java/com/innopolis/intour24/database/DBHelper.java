package com.innopolis.intour24.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ekaterina on 5/24/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance = null;
    private static final int CURRENT_VERSION = 1;

    private DBHelper(Context context) {
        super(context, com.innopolis.intour24.database.DBSchema.DB_NAME, null, CURRENT_VERSION);
    }


    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    /**
     * Creating all tables in db
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                com.innopolis.intour24.database.DBSchema.Tours.TABLE_NAME + "(" +
                com.innopolis.intour24.database.DBSchema.Tours.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBSchema.Tours.Columns.TOUR_NAME + " varchar(200)," +
                DBSchema.Tours.Columns.DESC + " text," +
                DBSchema.Tours.Columns.PRICE + " int," +
                DBSchema.Tours.Columns.CAPACITY + " int," +
                DBSchema.Tours.Columns.TIME + " long," +
                DBSchema.Tours.Columns.IS_LUNCH + " int," +
                DBSchema.Tours.Columns.START_PLACE + " text," +
                DBSchema.Tours.Columns.CATEGORY + " text," +
                DBSchema.Tours.Columns.BABY_PRICE + " int," +
                DBSchema.Tours.Columns.CHILD_PRICE + " int," +
                DBSchema.Tours.Columns.COMPANY + " text," +
                DBSchema.Tours.Columns.AVERAGE_RATING + " real," +
                DBSchema.Tours.Columns.SCHEDULE + " text," +
                DBSchema.Tours.Columns.LAT + " real," +
                DBSchema.Tours.Columns.LNG + " real," +
                DBSchema.Tours.Columns.DURATION + " long);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.Category.TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBSchema.Category.Columns.TOUR_ID + " int," +
                DBSchema.Category.Columns.TYPE + " text);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.SightTable.TABLE_NAME + "(" +
                DBSchema.SightTable.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBSchema.SightTable.Columns.TOURS_ID + " text," +
                DBSchema.SightTable.Columns.NAME + " text," +
                DBSchema.SightTable.Columns.DESC + " text," +
                DBSchema.SightTable.Columns.GEOPOSITION + " text," +
                DBSchema.SightTable.Columns.MAX_PRICE + " int," +
                DBSchema.SightTable.Columns.PICTURE_SRC + " text," +
                DBSchema.SightTable.Columns.MIN_PRICE + " int);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.RelTableToursSigns.TABLE_NAME +
                "(" +
                DBSchema.RelTableToursSigns.Columns.SIGN_ID + " int," +
                DBSchema.RelTableToursSigns.Columns.TOUR_ID + " int);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.SightProperties.TABLE_NAME +
                "(" +
                DBSchema.SightProperties.Columns.SIGN_ID + " int," +
                DBSchema.SightProperties.Columns.ICON_SRC + " text," +
                DBSchema.SightProperties.Columns.NAME + " text);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.ExcursionProperties.TABLE_NAME +
                "(" +
                DBSchema.ExcursionProperties.Columns.EXCURSION_ID + " int," +
                DBSchema.ExcursionProperties.Columns.ICON_SRC + " text," +
                DBSchema.ExcursionProperties.Columns.NAME + " text);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.ExcursionImages.TABLE_NAME +
                "(" +
                DBSchema.ExcursionImages.Columns.EXCURSION_ID + " int," +
                DBSchema.ExcursionImages.Columns.IMG_SRC + " text);"
        );

        createUserTable(db);
    }

    /**
     * Create the table for the user
     * @param database
     */
    private void createUserTable(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS " +
                DBSchema.UserTable.TABLE_NAME +
                "(" +
                DBSchema.UserTable.Columns.ID + " int," +
                DBSchema.UserTable.Columns.NAME + " text, " +
                DBSchema.UserTable.Columns.PHONE + " text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d("DBDebug", "UPGRADE");
        db.execSQL("DROP TABLE IF EXISTS " + DBSchema.DB_NAME);
        onCreate(db);
    }
}