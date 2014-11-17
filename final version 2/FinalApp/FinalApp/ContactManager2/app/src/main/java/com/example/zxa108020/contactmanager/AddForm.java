package com.example.zxa108020.contactmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AddForm extends Activity implements View.OnClickListener {

     EditText firstNameEdit; // This represents the EditText from the AddForm UI
     EditText lastNameEdit; // UI lastName
     EditText phoneNumberEdit; // UI phoneNumber
     EditText secondNumberEdit; // UI secondPhone
     Button saveContact; // UI Button to save the contact
     List<String> contactNames; // List to hold contact names
     ArrayAdapter<String> adapter; // List's adapter
     ListView contactList; // UI ListView to hold contact names
     Contact contact;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_form); // import the layout from XML
        initialize(); // initialize all variables
        saveContact.setOnClickListener(this);
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // when a list item is clicked
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //send an intent to MainActivity
                 // put firstName, lastName and phone numbers in the intent

                Intent intent = new Intent(AddForm.this, MainActivity.class);
                intent.putExtra("firstName", contact.getFirstName());
                intent.putExtra("lastName", lastNameEdit.getText().toString());
                intent.putExtra("phoneNumber", phoneNumberEdit.getText().toString());
                startActivity(intent);
            }
        });

    }
    public void initialize()
    {
        firstNameEdit = (EditText)findViewById(R.id.firstNameEditText);
        lastNameEdit = (EditText)findViewById(R.id.lastNameEditText);
        phoneNumberEdit = (EditText)findViewById(R.id.phoneNumberEditText);
        secondNumberEdit = (EditText)findViewById(R.id.secondPhoneEditText);
        saveContact = (Button)findViewById(R.id.saveContactButton);
        contactList = (ListView)findViewById(R.id.listView);
        contactNames = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactNames);
        contactList.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) { // when the save button is clicked
        if (v.getId() == R.id.saveContactButton)
        {
            if (!(firstNameEdit.getText().toString().equals(" ")))
            {
                String firstName = firstNameEdit.getText().toString();
                String lastName = lastNameEdit.getText().toString();
                String phoneNumber = phoneNumberEdit.getText().toString();
                String secondNumber = secondNumberEdit.getText().toString();

                contactNames.add(firstName + " " + lastName); // add the contact name to the list
                Collections.sort(contactNames); // sort the list
                adapter.notifyDataSetChanged(); // notify the adapter that the list has been changed
                contact = new Contact(firstName, lastName, phoneNumber, secondNumber);
                displayToast("Contact saved!"); // notify the user the contact has been saved
                firstNameEdit.setText(" ");
                lastNameEdit.setText(" ");
                phoneNumberEdit.setText(" ");
                secondNumberEdit.setText(" ");
            }
            else
            {
                displayToast("Please enter a first name.");
            }

        }
    }
    public void displayToast(String message)
    {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
