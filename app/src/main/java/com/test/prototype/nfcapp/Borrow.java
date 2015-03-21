package com.test.prototype.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Tom on 19/03/2015.
 */
public class Borrow extends Activity {

    ListView scannedItems;
    String newItemID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrow_layout);

        scannedItems =(ListView) findViewById(R.id.lvBorrowScannedItems);
    }

    public void buttonOnClickScanItem(View v){
        newItemID = "";
        TextView newItem = new TextView(getApplicationContext());

        newItem.setText("Randomly generated item (scanned)");

        scannedItems.addView(newItem);
    }
    public void buttonOnClickBorrowManuallyEnterItem(View v){
        newItemID = "";
        TextView newItem = new TextView(getApplicationContext());

        Intent startManualEntry = new Intent("android.intent.action.ManualEntry");
        startActivityForResult(startManualEntry, 0);

        newItem.setText("Manually entered item, ID: " + newItemID);

        scannedItems.addView(newItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // getting the chosen option out of the activity that just closed
            // (OpenedClass)

            newItemID = data.getStringExtra("id");
        } else if (resultCode == RESULT_CANCELED) {

        }
    }
}
