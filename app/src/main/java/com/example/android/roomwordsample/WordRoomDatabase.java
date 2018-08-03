package com.example.android.roomwordsample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Greta Grigutė on 2018-08-03.
 */

//Room is a database layer on top of an SQLite database.

//Annotation @Database means this class is a Room database, also we need to declare the entities that
// belong in the database and set the version number. When you modify the database schema, you'll
// need to update the version number and define how to handle migrations
// https://medium.com/google-developers/understanding-migrations-with-room-f01e04b07929.
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    // We define the DAOs that work with the database, add an abstract "getter" method for each @Dao.
    public abstract WordDao wordDao();

    // Making the WordRoomDatabase a singleton prevents having multiple instances of the DB opened at
    // the same time.
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {

                    // Code that creates a database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}

