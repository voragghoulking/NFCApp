package com.test.prototype.nfcapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Tom on 20/03/2015.
 */
public class AddNewItem extends Activity {

    Button scanId;
    Button takePicture;
    Button close;
    Button enterNewItem;
    EditText etId;
    EditText etName;
    EditText etDescription;
    Bitmap bmp;
    ImageView ivPicture;

    Item newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item_layout);

        //initializing the buttons, edit text, etc.
        scanId = (Button) findViewById(R.id.bScanNewItem);
        takePicture = (Button) findViewById(R.id.bTakePicture);
        close = (Button) findViewById(R.id.bAddNewItemClose);
        enterNewItem = (Button) findViewById(R.id.bEnterNewItem);
        etId = (EditText) findViewById(R.id.etANIItemId);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        ivPicture = (ImageView) findViewById(R.id.ivPicture);
    }

    //takes a picture
    public void buttonOnClickANITakePicture(View v){
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //activates the devices' camera (doesn't work on the emulator)
        startActivityForResult(i, 0);
    }

    //handles when the picture is taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            ivPicture.setImageBitmap(bmp);
        }
    }

    //button to cancel adding a new item (i.e. nothing happens if you press it)
    public void buttonOnClickANIClose(View v){
        finish();
    }

    //saves new item to the database, then closes intent
    public void buttonOnClickANIEnterNeItem(View v){
        newItem = new Item();
        if(etName.getText().length() > 0) {
            newItem.setName(etName.getText().toString());
        }
        if(etDescription.getText().length() > 0) {
            newItem.setDescription(etDescription.getText().toString());
        }
        if(etId.getText().length() > 0) {
            newItem.setId(etId.getText().toString());
        }
        if(bmp != null) {
            newItem.setImage(bmp);
        }

        mockServer.items.itemsList.put(newItem.getId(), newItem);

        finish();
/*
        etId.setText(newItem.getId());
        etName.setText(newItem.getName());
        etDescription.setText(newItem.getDescription());
        ivPicture.setImageBitmap(newItem.getImage());
*/
    }

}
