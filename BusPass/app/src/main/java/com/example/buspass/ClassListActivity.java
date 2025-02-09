package com.example.buspass;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClassListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClassAdapter classAdapter;
    private ArrayList<ClassModel> classList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);

        // Load data from the class table
        loadClassData();

        // Set the adapter
        classAdapter = new ClassAdapter(this, classList);
        recyclerView.setAdapter(classAdapter);
    }

    // Method to load data from the database
    private void loadClassData() {
        Cursor cursor = databaseHelper.getClassData();

        if (cursor.moveToFirst()) {
            do {
                ClassModel classModel = new ClassModel(
                        cursor.getString(1), // name
                        cursor.getString(2), // gender
                        cursor.getString(3), // contact
                        cursor.getString(4), // category
                        cursor.getString(5), // source
                        cursor.getString(6)  // destination
                );
                classList.add(classModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}
