package com.example.Presenters;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;

import com.example.Helpers.WordsSqlService;
import com.example.Interfaces.MainActivityApi;
import com.example.Interfaces.SqlService;
import com.example.Interfaces.WordsActivityApi;
import com.google.android.gms.common.api.Api;


/**
 * Created by Sergey on 7/6/2017.
 */

public class WordsActivityPresenter implements MainActivityApi {
    private Context mCtx;
    private WordsActivityApi mApi;
    private SqlService mService;

    public WordsActivityPresenter(Context ctx, WordsActivityApi mApi) {
        this.mCtx = ctx;
        this.mApi = mApi;
        mService = new WordsSqlService(mCtx);
    }

    public void startAnimation() {
        mApi.getLoadingIndicator().show();
        Runnable progress = new Runnable() {
            @Override
            public void run() {
                mApi.getLoadingIndicator().hide();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progress, 3000);
    }

    public Cursor getWords() {
        Cursor cursor = mService.getWordsWithinCategory(mApi.getCategoryName());
        mApi.getLoadingIndicator().hide();
        return cursor;
    }

}
