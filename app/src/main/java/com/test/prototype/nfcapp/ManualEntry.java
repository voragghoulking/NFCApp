package com.test.prototype.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Tom on 20/03/2015.
 * handles manual entry of items ids for whenever the scanning doesn't work
 */
public class ManualEntry extends Activity {
    EditText etItemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_entry_layout);

        //initializing the buttons, edit text, etc.
        etItemId = (EditText) findViewById(R.id.etItemID);
    }

    //saves the manually entered id and closes this intent
    public void buttonOnClickStocktakeManualEntryItemId(View v){
        Intent itemIdToReturn = new Intent();
        String itemId = etItemId.getText().toString();

        //checks if an id was entered
        if (itemId.length() > 0) {//add id to extras so the previous intent can get it
            itemIdToReturn.putExtra("id", itemId);

            setResult(RESULT_OK, itemIdToReturn);
        }else{//inform app that no id was entered so do nothing on intent closing
            setResult(RESULT_CANCELED);
        }
        finish();
    }

//backup in-case this intent is hidden but not closed
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        setResult(RESULT_CANCELED);
        finish();
    }
}
