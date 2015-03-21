package com.test.prototype.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

/**
 * Created by Tom on 19/03/2015.
 */
public class Management extends Activity {

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_layout);
    }

    public void buttonOnClickStocktake(View v){
        Intent openStocktakeActivity = new Intent("android.intent.action.STOCKTAKE");
        startActivity(openStocktakeActivity);
    }

    public void buttonOnClickAddNewItem(View v){
        Intent openAddNewItemActivity = new Intent("android.intent.action.ADDNEWITEM");
        startActivity(openAddNewItemActivity);
    }

}
