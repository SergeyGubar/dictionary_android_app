package com.example.Interfaces;

import android.database.Cursor;

import com.example.Helpers.WordsSqlService;
import com.example.android.app.Word;

/**
 * Created by Sergey on 8/20/2017.
 */

public interface SqlService {

    void addWord(Word word);

    Cursor getWordsWithinCategory(String category);

    boolean removeWord(long id);

}