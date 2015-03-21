package com.test.prototype.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Tom on 20/03/2015.
 */
public class Stocktake extends Activity{
    String itemID;
    TextView tvItemsList;
    Button bFinishStocktake;
    Button bManualEntry;
    int numItems = 0;

    //will be replaced once the db/server is running
    HashMap<String, Item> dbItems = new HashMap<String, Item>();
    ArrayList<String> scannedItems = new ArrayList<String>();


    // what was out of date?
    //that warning down the bottom ok
    //does the program run?
    //yup
    //hmm hold on ill try to find on google
    //ok, i'll keep trying over here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stocktake_layout);

        //initializing the buttons, edit text, etc.
        tvItemsList = (TextView) findViewById(R.id.tvItemsList);
        bFinishStocktake = (Button) findViewById(R.id.bStocktakeClose);
        bManualEntry = (Button) findViewById(R.id.bStockTakeManualEntry);
    }

    //manual entry of an item's id
    public void buttonOnClickStocktakeManuallyEnterItem(View v){
        itemID = "";
        TextView newItem = new TextView(getApplicationContext());

        //opens dialog intent "manual entry"
        Intent startManualEntry = new Intent("android.intent.action.MANUALENTRY");
        startActivityForResult(startManualEntry, 0);

        //newItem.setText("Manually entered item, ID: " + itemID);

    }

    //handles when the id has been manually entered
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            itemID = data.getStringExtra("id");
            scannedItems.add(scannedItems.size(), itemID);
            numItems++;
            tvItemsList.setText(tvItemsList.getText() + "\n Item# " + numItems + ": " + itemID);
        } else if (resultCode == RESULT_CANCELED) {

        }
    }

    //sorts out the end of the stock-take (will drastically change when the server becomes available)
    public void buttonOnClickStocktakeFinishStocktake(View v){

        dbItems = mockServer.items.itemsList;
        bManualEntry.setEnabled(false);
        bFinishStocktake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bFinishStocktake.setText("Close");

        HashMap<String, Item> missingItems = new HashMap<String, Item>();
        HashMap<String, Item> foundItems = new HashMap<String, Item>();

        tvItemsList.setText("List of items that were not found in the stock-take:");

        //boolean itemFound = false;

        //itemFound = false;
        for (int j = 0; j < scannedItems.size(); j++) {
            if (dbItems.containsKey(scannedItems.get(j))) {
                //itemFound = true;
                foundItems.put(scannedItems.get(j), dbItems.get(scannedItems.get(j)));
                scannedItems.remove(j);
                j--;
                //break;
            }
        }

        Set missingKeys = dbItems.keySet();
        missingKeys.removeAll(foundItems.keySet());

        Collection missingValues = dbItems.values();
        missingValues.removeAll(foundItems.values());

        for(int i = 0; i < missingKeys.size(); i++){
            String key = missingKeys.toArray()[i].toString();
            Item value = (Item)missingValues.toArray()[i];
            missingItems.put(key, value);
        }

        for(int i = 0; i < missingItems.size(); i++){
            tvItemsList.setText(tvItemsList.getText() + "\n" + missingItems.keySet().toArray()[i]);
        }

        tvItemsList.setText(tvItemsList.getText() + "\nList of items that were scanned but not in the database:");

        for(int i = 0; i < scannedItems.size(); i++){
            tvItemsList.setText(tvItemsList.getText() + "\n" + scannedItems.get(i));
        }
        //finish();

    }
}
