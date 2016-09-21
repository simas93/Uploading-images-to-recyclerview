package com.example.simas.fourthexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.simas.fourthexercise.Model.Nature;
import com.example.simas.fourthexercise.Model.helper.Constants;
import com.example.simas.fourthexercise.Model.helper.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simas on 8/15/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE.DATABASE_NAME, null, Constants.DATABASE.VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Constants.DATABASE.CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Constants.DATABASE.DROP_QUERY);
        onCreate(sqLiteDatabase);
    }

    public  void insertData (Nature nature){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.DATABASE.PHOTO_NAME, nature.getPhotoName());
        contentValues.put(Constants.DATABASE.PHOTO_URL, nature.getPhotoUrl());
        sqLiteDatabase.insert(Constants.DATABASE.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<Nature> getNature () {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Constants.DATABASE.GET_NATURE_QUERY, null);
        System.out.println(Constants.DATABASE.GET_NATURE_QUERY);
        List<Nature> natureList = new ArrayList<>();

        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    Nature nature = new Nature();
                    nature.setPhotoName(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PHOTO_NAME)));
                    nature.setPhotoUrl(cursor.getString(cursor.getColumnIndex(Constants.DATABASE.PHOTO_URL)));
                    natureList.add(nature);
                } while (cursor.moveToNext());
            }
        } else {
            System.out.println("Tuscia duombaze");
        }

        return natureList;
    }
}
