package com.example.android.roomwordsample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Greta Grigutė on 2018-08-03.
 */

//The ViewModel's role is to provide data to the UI and survive configuration changes.
// A ViewModel acts as a communication center between the Repository and the UI.
// You can also use a ViewModel to share data between fragments.
// A ViewModel must never reference a view, Lifecycle, or any class that may hold a reference
// to the activity context.
// WARNING: Never pass context into ViewModel instances. Do not store Activity, Fragment, or View
// instances or their Context in the ViewModel.

//ViewModel and onSavedInstanceState():
// https://medium.com/google-developers/viewmodels-persistence-onsaveinstancestate-restoring-ui-state-and-loaders-fc7cc4a6c090

public class WordViewModel extends AndroidViewModel {
    //variable to hold the reference to Repository.
    private WordRepository mRepository;

    //variable to cache the list of words.
    private LiveData<List<Word>> mAllWords;

    // public constructor that gets a reference to the repository and gets the list of words from
    // the repository.
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    // a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    //a wrapper insert() method that calls the Repository's insert() method. In this way, the
    // implementation of insert() is completely hidden from the UI.
    public void insert(Word word) {
        mRepository.insert(word);
    }
}
