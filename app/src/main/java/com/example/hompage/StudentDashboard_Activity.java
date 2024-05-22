package com.example.hompage;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDashboard_Activity extends AppCompatActivity {

    private Spinner spinnerType;
    private String[] userTypes = {"Teacher", "Student"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addStudentemail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void viewMarks (View view) {
        // Handle the click event here
        // For example, show a toast message
        Toast.makeText(this, "Work Under Progress...", Toast.LENGTH_SHORT).show();
    }
    public void viewAttendance (View view) {
        // Handle the click event here
        // For example, show a toast message
        Toast.makeText(this, "Work Under Progress...", Toast.LENGTH_SHORT).show();
    }
    public void viewAssignments (View view) {
        // Handle the click event here
        // For example, show a toast message
        Toast.makeText(this, "Work Under Progress...", Toast.LENGTH_SHORT).show();
    }
}