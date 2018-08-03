package com.example.android.roomwordsample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Greta Grigutė on 2018-08-03.
 */

//The DAO (data access object), you specify SQL queries and associate them with method calls.
// The compiler checks the SQL and generates queries from convenience annotations for common
// queries, such as @Insert.
//
//The DAO must be an interface or abstract class.
//
//By default, all queries must be executed on a separate thread.
//
//Room uses the DAO to create a clean API for your code.

// Annotated class as @Dao to identify a DAO class for room
@Dao
public interface WordDao {
    //a method to insert one word.
    // Annotated @Insert -  You don't have to provide any SQL! (There are also @Delete and @Update
    // annotations for deleting and updating a row)
    @Insert
    void insert(Word word);

    //a method to delete all rows.
    //There is no convenience annotation for deleting all rows, need to use @Query annotation and
    // provide the SQL query as a string parameter to @Query.
    @Query("DELETE FROM word_table")
    void deleteAll();

    //a method to get all the words, which returns list of words.
    //annotated with the @Query with provided SQL query adding ORDER BY, though it is not necessary
    //for this app, but  by default, order is not guaranteed, and ordering makes testing straightforward.
    @Query("SELECT * from word_table ORDER BY word ASC")
    List<Word> getAllWords();

    // When inserting data, you can provide a conflict strategy.
    // As the word is primary key, and the default SQL behavior is ABORT, so that you cannot insert
    // two items with the same primary key into the database.

    // but in other cases might be useful to provide conflict strategy:if the table has more than
    // one column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE)to replace a row.

}
