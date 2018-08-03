package com.example.android.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Greta Grigutė on 2018-08-03.
 */

// Annotation to the class declaration to indicate that it's an entity.
// Specified name of the table (do it if you want it to be different from the name of the class).
@Entity(tableName = "word_table")
public class Word {
    //Every entity needs a primary key.
    //To keep things simple, in this example each word acts as its own primary key.
    // You can autogenerate unique keys by using private int id; and annotating it as follows :
    // @PrimaryKey(autoGenerate = true)

    @PrimaryKey
    //Denotes that a parameter, field, or method return value can never be null.
    @NonNull
    //Specifies the name of the column in the table (do it if you want it to be different from the
    //name of the member variable).
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {
        this.mWord = word;
    }

    //Every field that's stored in the database needs to be either public or have a "getter" method.
    //This sample provides a getWord() method.
    public String getWord() {
        return this.mWord;
    }
}
