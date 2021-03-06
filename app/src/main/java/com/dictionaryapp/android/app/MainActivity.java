/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dictionaryapp.android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.dictionaryapp.fragments.WordsFragment;
import com.dictionaryapp.helpers.FirebaseService;
import com.dictionaryapp.presenters.MainActivityPresenter;
import com.dictionaryapp.interfaces.MainActivityApi;


public class MainActivity extends AppCompatActivity implements MainActivityApi, WordsFragment.WordFragmentListener {

    private static final String TAG = "MainActivity";
    private MainActivityPresenter mPresenter;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.words_main_pager);
        WordsFragmentAdapter adapter = new WordsFragmentAdapter(getSupportFragmentManager(), this);
        mPresenter = new MainActivityPresenter(this, this);
        mViewPager.setAdapter(adapter);
        TabLayout tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        tabs.setupWithViewPager(mViewPager);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.float_btn);
        mFloatingActionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.startAddActivity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_action:
                FirebaseService.logOut(this);
                break;
            case R.id.new_category_menu_item:
                mPresenter.showNewCategoryAddDialog();
                break;
            case R.id.delete_category_action:
                Intent deleteActivityIntent = new Intent(this, DeleteCategoryActivity.class);
                startActivity(deleteActivityIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() called");
        super.onResume();
    }


    @Override
    public void updateAdapter() {
        mViewPager.setAdapter(new WordsFragmentAdapter(getSupportFragmentManager(), this));
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void hideFab() {
        mFloatingActionButton.hide();
    }

    @Override
    public void showFab() {
        mFloatingActionButton.show();
    }

    @Override
    public boolean isFabShown() {
        return mFloatingActionButton.isShown();
    }

    @Override
    public CoordinatorLayout getContainer() {
        return (CoordinatorLayout) findViewById(R.id.main_coordinator_layout);
    }


}
