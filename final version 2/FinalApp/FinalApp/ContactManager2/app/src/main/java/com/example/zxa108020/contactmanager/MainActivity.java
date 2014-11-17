package com.example.zxa108020.contactmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements View.OnClickListener {

    Button callButton;
    Button textButton;
    Button pictureButton;
    Button blockButton;
    TextView tx;

    String firstName;
    String lastName;
    String phoneNumber;
    String secondPhone;

    ImageView iv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        callButton.setOnClickListener(this);
        textButton.setOnClickListener(this);
        pictureButton.setOnClickListener(this);
        blockButton.setOnClickListener(this);
        firstName = getIntent().getExtras().getString("firstName");

        tx = (TextView) findViewById(R.id.textView6);
        tx.setText(firstName);
    }
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.callButton:
                performCall(getIntent().getExtras().getString("phoneNumber"));
                break;

            case R.id.textButton:
                sendSMS(getIntent().getExtras().getString("phoneNumber"));
                break;

            case R.id.pictureButton:
                setPicture();
                break;

            case R.id.blockButton:
                block(getIntent().getExtras().getString("phoneNumber"));
                break;
        }
    }
    public void initialize()
    {
        callButton = (Button)findViewById(R.id.callButton);
        textButton = (Button)findViewById(R.id.textButton);
        pictureButton = (Button)findViewById(R.id.pictureButton);
        blockButton = (Button)findViewById(R.id.blockButton);
        iv = (ImageView)findViewById(R.id.imageView);


    }
    public void performCall(String number)
    {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + Uri.encode(number.trim())));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }
    public void sendSMS(String number)
    {
        Intent msgIntent = new Intent(Intent.ACTION_VIEW);
        msgIntent.setType("vnd.android-dir/mms-sms");
        msgIntent.putExtra("address", number);
        msgIntent.putExtra("sms_body", "Type something here..");
        startActivity(msgIntent);

    }
    public void setPicture()
    {
        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (picIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(picIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            iv.setImageDrawable(null);
            iv.destroyDrawingCache();
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap)extras.get("data");
            iv.setImageBitmap(bitmap);
        }
    }
    public void block(String number)
    {
        number = null;
    }
}
