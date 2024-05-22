package com.example.hompage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addStudentemail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void addAStudent (View view) {

        Intent intent = new Intent(this, addStudent.class);
        startActivity(intent);
    }
    public void viewStudents (View view) {

        Toast.makeText(this, "Currently not Availabe", Toast.LENGTH_SHORT).show();
    }
    public void updateStudent (View view) {

        Intent intent = new Intent(this, studentDataUpdate.class);
        startActivity(intent);
    }


    public void AddStudyRes (View view) {
        // Handle the click event here
        // For example, show a toast message
        Toast.makeText(this, "Currently not Availabe", Toast.LENGTH_SHORT).show();
    }
}