package com.example.activitiesandstorage3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddNoteActivity extends AppCompatActivity {

    Spinner spSelectionForDelete;
    ArrayAdapter listAdapter;
    ArrayList<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spSelectionForDelete = findViewById(R.id.spSelectionForDelete);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spSelectionForDelete.setAdapter(listAdapter);


    }

    public void onAddNoteClick(View view) {
        EditText txtNoteTitle = findViewById(R.id.txtNote);
        EditText txtNoteContent = findViewById(R.id.textNote);

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();
        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        Set<String> newStrSet = new HashSet<String>();
        newStrSet.add(txtNoteTitle.getText().toString() + "\n" + txtNoteContent.getText().toString());
        newStrSet.addAll(oldSet);
        spEd.putStringSet("notes",newStrSet);
        spEd.apply();



        if (txtNoteTitle.getText().toString().isEmpty()) {
            Toast.makeText(this, "Fill in the title!", Toast.LENGTH_SHORT).show();
            return;

        }

        if (txtNoteContent.getText().toString().isEmpty()){
            Toast.makeText(this, "Fill in the content!", Toast.LENGTH_SHORT).show();
            return;
        }

        finish();
    }

}
