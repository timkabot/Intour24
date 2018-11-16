package com.innopolis.intour24.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.innopolis.intour24.model.entity.Excursion;
import com.innopolis.intour24.model.entity.GroupProperty;
import com.innopolis.intour24.model.entity.Sight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekaterina on 5/24/17.
 */

public class DBRepo implements DBRepoInterface {
    private static final String TAG = "DBDebug";
    private DBHelper mHelper;

    public DBRepo(Context context) {
        mHelper = DBHelper.getInstance(context);
    }

    /**
     * @param signName name of specific sign
     * @return Excursion List with specific name
     */
    @Override
    public ArrayList<Excursion> getExcursionList(String signName) {
        ArrayList<Excursion> lExcursions = new ArrayList<>();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        int signId = getSightId(signName);

        /*
        SELECT * FROM Tours
        INNER JOIN Tours_ID ON RelTableToursSigns.Tours_ID = Tours.ID
        WHERE RelTableToursSigns.Signs_ID = signId;
         */
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBSchema.Tours.TABLE_NAME +
                " INNER JOIN " + DBSchema.RelTableToursSigns.TABLE_NAME + " ON " + DBSchema.RelTableToursSigns.TABLE_NAME + "." +
                DBSchema.RelTableToursSigns.Columns.TOUR_ID + "=" + DBSchema.Tours.TABLE_NAME + "." + DBSchema.Tours.Columns.ID +
                " WHERE " + DBSchema.RelTableToursSigns.TABLE_NAME + "." + DBSchema.RelTableToursSigns.Columns.SIGN_ID + "=" + signId, null);

        if (cursor.moveToFirst()) {
            do {

                lExcursions.add(getExcursion(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return lExcursions;
    }

    private boolean getFromPseudoBool(int pseudoBool) {
        return pseudoBool == 1;
    }

    /**
     * Get all signs
     *
     * @return
     */
    @Override
    public ArrayList<Sight> getSightsList() {
        ArrayList<Sight> lSights = new ArrayList<>();
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.SightTable.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                lSights.add(getSight(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return lSights;
    }

    /**
     * Get specific signs for some tour
     */
    @Override
    public ArrayList<Sight> getSights(String tourName) {
        ArrayList<Sight> sights = new ArrayList<>();
        SQLiteDatabase database = mHelper.getReadableDatabase();

        int tourId = getExcursionId(tourName);

        /*
        SELECT * FROM Signs
        INNER JOIN Tours_ID ON RelTableToursSigns.Signs_ID = Signs.ID
        WHERE RelTableToursSigns.Tours_ID = tourId;
         */
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBSchema.SightTable.TABLE_NAME +
                " INNER JOIN " + DBSchema.RelTableToursSigns.TABLE_NAME + " ON " + DBSchema.RelTableToursSigns.TABLE_NAME + "." +
                DBSchema.RelTableToursSigns.Columns.SIGN_ID + "=" + DBSchema.SightTable.TABLE_NAME + "." + DBSchema.SightTable.Columns.ID +
                " WHERE " + DBSchema.RelTableToursSigns.TABLE_NAME + "." + DBSchema.RelTableToursSigns.Columns.TOUR_ID + "=" + tourId, null);
        if (cursor.moveToFirst()) {
            do {
                sights.add(getSight(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return sights;
    }

    /**
     * @param excursionName id of what tour we want to find
     * @return id of tour with that name
     */
    private int getExcursionId(String excursionName) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.Tours.TABLE_NAME, null, null, null, null, null, null);
        boolean flag = false;
        if (cursor.moveToFirst()) {
            int tourNameIndex = cursor.getColumnIndex(DBSchema.Tours.Columns.TOUR_NAME);
            do {
                if (excursionName.equals(cursor.getString(tourNameIndex))) {
                    flag = true;
                    break;
                }
            } while (cursor.moveToNext());
        }

        if (!flag) return -1;//Such element was not found
        int id = cursor.getPosition();
        cursor.close();
        return id;
    }


    /**
     * Iterates through all fields of signs and trying to find right one
     *
     * @param name of searched sight
     * @return id of sight with such name
     */
    private int getSightId(String name) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.SightTable.TABLE_NAME, null, null, null, null, null, null);
        boolean flag = false;

        if (cursor.moveToFirst()) {
            int signNameIndex = cursor.getColumnIndex(DBSchema.SightTable.Columns.NAME);

            do {
                if (name.equals(cursor.getString(signNameIndex))) {
                    flag = true;
                    break;
                }
            } while (cursor.moveToNext());
        }

        if (!flag) return -1;//such element was not found
        int id = cursor.getPosition();
        cursor.close();
        return id;
    }

    //TODO Properties
    @Override
    public void addExcursion(Excursion excursion) {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.Tours.Columns.ID, excursion.getId());
        cv.put(DBSchema.Tours.Columns.DURATION, excursion.getDuration());
        cv.put(DBSchema.Tours.Columns.TOUR_NAME, excursion.getName());
        cv.put(DBSchema.Tours.Columns.DESC, excursion.getDescription());
        cv.put(DBSchema.Tours.Columns.CAPACITY, excursion.getCapacity());

        // addExcursionImgSrc(excursion.getId(), excursion.getImages());

        SQLiteDatabase database = mHelper.getWritableDatabase();

        if (getExcursionId(excursion.getName()) == -1) {
            Log.d(TAG, "Insert new excursion: " + excursion.getName());
            database.insert(DBSchema.Tours.TABLE_NAME, null, cv);

        } else {
            Log.d(TAG, "Update excursion: " + excursion.getName());
            database.update(DBSchema.Tours.TABLE_NAME, cv,/*WHERE Tours.Name = excursion.getName()*/
                    DBSchema.Tours.TABLE_NAME + "." + DBSchema.Tours.Columns.TOUR_NAME + "=" +
                            "\'" + excursion.getName() + "\'", null);
        }

    }

    @Override
    public void addSight(Sight sight) {
        ContentValues cv = new ContentValues();
        cv.put(DBSchema.SightTable.Columns.ID, sight.getId());
        cv.put(DBSchema.SightTable.Columns.NAME, sight.getName());
        cv.put(DBSchema.SightTable.Columns.DESC, sight.getDescription());
        cv.put(DBSchema.SightTable.Columns.GEOPOSITION, sight.getGeoposition());
        cv.put(DBSchema.SightTable.Columns.MAX_PRICE, sight.getMaxPrice());
        cv.put(DBSchema.SightTable.Columns.MIN_PRICE, sight.getMinPrice());

        SQLiteDatabase database = mHelper.getWritableDatabase();

        if (getSightId(sight.getName()) == -1) {
            Log.d(TAG, "Insert new sight: " + sight.getName());
            database.insert(DBSchema.SightTable.TABLE_NAME, null, cv);
        } else {
            Log.d(TAG, "Update excursion: " + sight.getName());
            database.update(DBSchema.SightTable.TABLE_NAME, cv, /*WHERE Signs.Name = sight.getName*/
                    DBSchema.SightTable.TABLE_NAME + "." + DBSchema.SightTable.Columns.NAME + "=" +
                            "\'" + sight.getName() + "\'", null);
        }
        database.close();
    }

    @Override
    public void addSight(ArrayList<Sight> lSights) {
        for (Sight sign : lSights) {
            addSight(sign);
        }
    }

    @Override
    public void addRel(Sight sight, Excursion excursion) {
        addRel(sight.getName(), excursion.getName());
    }

    @Override
    public void addRel(String signName, String excursionName) {
        int signId = getSightId(signName);
        int excursionId = getExcursionId(excursionName);

        Log.d(TAG, signId + " " + excursionId);

        ContentValues cv = new ContentValues();
        cv.put(DBSchema.RelTableToursSigns.Columns.SIGN_ID, signId);
        cv.put(DBSchema.RelTableToursSigns.Columns.TOUR_ID, excursionId);

        SQLiteDatabase database = mHelper.getWritableDatabase();

        if (!isInTable(signId, excursionId)) {
            database.insert(DBSchema.RelTableToursSigns.TABLE_NAME, null, cv);
        }
    }

    private int getPseudoBool(Boolean bool) {
        if (bool)
            return 1;
        return 0;
    }


    @Override
    public boolean isInTable(int signId, int excursionId) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.RelTableToursSigns.TABLE_NAME, null, null, null, null, null, null);
        int signColumn = cursor.getColumnIndex(DBSchema.RelTableToursSigns.Columns.SIGN_ID);
        int excursionColumn = cursor.getColumnIndex(DBSchema.RelTableToursSigns.Columns.TOUR_ID);
        if (cursor.moveToFirst()) {
            do {
                if ((cursor.getInt(signColumn) == signId) && (cursor.getInt(excursionColumn) == excursionId)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    @Override
    public ArrayList<Sight> searchSights(String text) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " +
                DBSchema.SightTable.TABLE_NAME + " WHERE " + DBSchema.SightTable.Columns.NAME + " LIKE \'%" +
                text + "%\'", null);

        //Cursor cursor = database.query(DBSchema.SightTable.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Sight> lSights = new ArrayList<>();
        Sight sight;
        if (cursor.moveToFirst()) {
            do {
                Log.d(TAG, "Some info in Cursor");
                sight = new Sight();
                sight.setId(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.ID)));
                sight.setName(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.NAME)));
                sight.setDescription(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.DESC)));
                sight.setGeoposition(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.GEOPOSITION)));
                sight.setMaxPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MAX_PRICE)));
                sight.setMinPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MIN_PRICE)));
                lSights.add(sight);
            } while (cursor.moveToNext());
        }
        return lSights;
    }

    /**
     * @param signName
     * @return sign from its name
     */
    @Override
    public Sight getSight(String signName) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.SightTable.TABLE_NAME, null, /*WHERE SightTable.NAME = "signName"*/
                DBSchema.SightTable.TABLE_NAME + "." + DBSchema.SightTable.Columns.NAME + "=" + "\'" + signName + "\'", null, null, null, null);

        Sight sight = new Sight();
        if (cursor.moveToFirst()) {
            sight.setId(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.ID)));
            sight.setName(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.NAME)));
            sight.setDescription(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.DESC)));
            sight.setGeoposition(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.GEOPOSITION)));
            sight.setMaxPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MAX_PRICE)));
            sight.setMinPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MIN_PRICE)));
        }

        cursor.close();

        return sight;
    }

    @Override
    public Excursion getExcursion(String name) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.Tours.TABLE_NAME, null, /*WHERE Excursion.NAME = "name"*/
                DBSchema.Tours.TABLE_NAME + "." + DBSchema.Tours.Columns.TOUR_NAME + "=" + "\'" + name + "\'", null, null, null, null);
        Excursion excursion = null;
        if (cursor.moveToFirst()) {
            excursion = getExcursion(cursor);
        }

        return excursion;
    }

    @Override
    public void addExcursions(ArrayList<Excursion> lExcursion) {
        for (Excursion excursion : lExcursion) {
            addExcursion(excursion);
        }
    }

    public ArrayList<Sight> getSightById(int id) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.SightTable.TABLE_NAME, null, /*WHERE Sight.ID = "id"*/
                DBSchema.SightTable.TABLE_NAME + "." + DBSchema.SightTable.Columns.ID + "=" + "\'" + id + "\'", null, null, null, null);

        ArrayList<Sight> lSight = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Sight sight = new Sight();
                sight.setId(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.ID)));
                sight.setName(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.NAME)));
                sight.setDescription(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.DESC)));
                sight.setGeoposition(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.GEOPOSITION)));
                sight.setMaxPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MAX_PRICE)));
                sight.setMinPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MIN_PRICE)));
                lSight.add(sight);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return lSight;
    }

    public Excursion getExcursionById(int id) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.Tours.TABLE_NAME, null, /*WHERE Tour.ID = "id"*/
                DBSchema.Tours.TABLE_NAME + "." + DBSchema.SightTable.Columns.ID + "=" + "\'" + id + "\'", null, null, null, null);

        Excursion excursion = null;
        if (cursor.moveToFirst()) {
            excursion = getExcursion(cursor);
        }
        cursor.close();

        return excursion;
    }

    private Sight getSight(Cursor cursor) {
        Sight sight = new Sight();
        sight.setId(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.ID)));
        sight.setName(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.NAME)));
        sight.setDescription(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.DESC)));
        sight.setGeoposition(cursor.getString(cursor.getColumnIndex(DBSchema.SightTable.Columns.GEOPOSITION)));
        sight.setMaxPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MAX_PRICE)));
        sight.setMinPrice(cursor.getInt(cursor.getColumnIndex(DBSchema.SightTable.Columns.MIN_PRICE)));

        return sight;
    }

    private Excursion getExcursion(Cursor cursor) {
        Excursion excursion = new Excursion();
        excursion.setId(cursor.getInt(cursor.getColumnIndex(DBSchema.Tours.Columns.ID)));
        excursion.setDuration(cursor.getLong(cursor.getColumnIndex(DBSchema.Tours.Columns.DURATION)));
        excursion.setName(cursor.getString(cursor.getColumnIndex(DBSchema.Tours.Columns.TOUR_NAME)));
        excursion.setDescription(cursor.getString(cursor.getColumnIndex(DBSchema.Tours.Columns.DESC)));
        excursion.setCapacity(cursor.getInt(cursor.getColumnIndex(DBSchema.Tours.Columns.CAPACITY)));
        //  excursion.setImages(readExcursionImgSrc(excursion.getId()));

        //TODO Properties
        //TODO categoryProperty


        return excursion;
    }

    private ArrayList<GroupProperty> readExcursionProperties(int id) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.ExcursionProperties.TABLE_NAME, null, /*WHERE Excursion_property.Excursion_ID = "id"*/
                DBSchema.ExcursionProperties.TABLE_NAME + "." + DBSchema.ExcursionProperties.Columns.EXCURSION_ID + "=" + "\'" + id + "\'", null, null, null, null);

        ArrayList<GroupProperty> lProperties = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                GroupProperty groupProperty = new GroupProperty();

                groupProperty.setIcon(cursor.getString(cursor.getColumnIndex(DBSchema.ExcursionProperties.Columns.ICON_SRC)));
                groupProperty.setName(cursor.getString(cursor.getColumnIndex(DBSchema.ExcursionProperties.Columns.NAME)));

                lProperties.add(groupProperty);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return lProperties;
    }


    private ArrayList<GroupProperty> readSightProperties(int id) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.SightProperties.TABLE_NAME, null, /* WHERE Excursion_property.Excursion_ID = "id" */
                DBSchema.SightProperties.TABLE_NAME + "." + DBSchema.SightProperties.Columns.SIGN_ID + "=" + "\'" + id + "\'", null, null, null, null);

        ArrayList<GroupProperty> lProperties = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                GroupProperty groupProperty = new GroupProperty();

                groupProperty.setIcon(cursor.getString(cursor.getColumnIndex(DBSchema.ExcursionProperties.Columns.ICON_SRC)));
                groupProperty.setName(cursor.getString(cursor.getColumnIndex(DBSchema.ExcursionProperties.Columns.NAME)));


                lProperties.add(groupProperty);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return lProperties;
    }

    private ArrayList<String> readExcursionImgSrc(int id) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(DBSchema.ExcursionImages.TABLE_NAME, null, /* WHERE Excursion_Images.EXCURSION_ID = id */
                DBSchema.ExcursionImages.TABLE_NAME + "." + DBSchema.ExcursionImages.Columns.EXCURSION_ID + "=" + "\'" + id + "\'", null, null, null, null);

        ArrayList<String> imgSrc = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                imgSrc.add(cursor.getString(cursor.getColumnIndex(DBSchema.ExcursionImages.Columns.IMG_SRC)));

            } while (cursor.moveToNext());

        }
        cursor.close();

        return imgSrc;
    }

    private void addExcursionImgSrc(int id, List<String> imgSrc){
        ContentValues cv = new ContentValues();
        SQLiteDatabase database = mHelper.getWritableDatabase();

        if(imgSrc == null) return;

        for(String src : imgSrc){
            if(!readExcursionImgSrc(id).contains(src)){
                cv.put(DBSchema.ExcursionImages.Columns.EXCURSION_ID, id);
                cv.put(DBSchema.ExcursionImages.Columns.IMG_SRC, src);

                database.insert(DBSchema.ExcursionImages.TABLE_NAME, null, cv);
            }
        }

        database.close();
    }

    private void addExcursionProperties(int id, List<GroupProperty> properties){
        ContentValues cv = new ContentValues();
        SQLiteDatabase database = mHelper.getWritableDatabase();

        if(properties == null) return;

        for (GroupProperty prop : properties){
            if(!readExcursionProperties(id).contains(prop)){
                cv.put(DBSchema.ExcursionProperties.Columns.EXCURSION_ID, id);
                cv.put(DBSchema.ExcursionProperties.Columns.ICON_SRC, prop.getIcon());
                cv.put(DBSchema.ExcursionProperties.Columns.NAME, prop.getName());

                database.insert(DBSchema.ExcursionProperties.TABLE_NAME, null, cv);
            }
        }

        database.close();
    }



}

