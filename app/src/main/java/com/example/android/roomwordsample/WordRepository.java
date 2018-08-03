package com.example.android.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Greta Grigutė on 2018-08-03.
 */
//A Repository is a class that abstracts access to multiple data sources. A Repository manages query
// threads and allows you to use multiple backends. In the most common example, the Repository
// implements the logic for deciding whether to fetch data from a network or use results cached
// in a local database.
public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    // A constructor that gets a handle to the database and initializes the member variables.
    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    // A wrapper for getAllWords(). Room executes all queries on a separate thread. Observed
    // LiveData will notify the observer when the data has changed.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // A wrapper for the insert() method. You must call this on a non-UI thread or your app will
    // crash. Room ensures that you don't do any long-running operations on the main thread,
    // blocking the UI.
    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    //AsyncTask method
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
