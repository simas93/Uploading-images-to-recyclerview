package com.example.simas.fourthexercise.Model.helper;

/**
 * Created by simas on 8/16/2016.
 */
public class Constants {

    public static final class HTTP {
        public static final String BASE_URL = "https://api.myjson.com";
    }

    public static final class DATABASE {
        public  static  final String DATABASE_NAME = "Nature.db";
        public  static final String TABLE_NAME = "Nature_Photos";
        public  static final int VERSION = 6;

        public  static final String PHOTO_NAME = "photo_name";
        public  static final String PHOTO_URL = "photo_url";

        public static final String DROP_QUERY = "DROP TABLE IF EXISTS " +TABLE_NAME;

        public static final String GET_NATURE_QUERY = "SELECT * FROM " +TABLE_NAME;

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                PHOTO_NAME +" TEXT not null unique," +
                PHOTO_URL + " TEXT not null unique)";


    }

}
