package com.example.activitiesandstorage3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {

    Spinner spSelectionForDelete;

    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;

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


    public void onRemoveNoteClick(View view) {
        String SelectionForDelete = spSelectionForDelete.getSelectedItem().toString();

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        oldSet.remove(SelectionForDelete);


        spEd.putStringSet("notes", oldSet);
        spEd.apply();

    }
}

