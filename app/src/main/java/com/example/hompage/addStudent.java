package com.example.hompage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addStudent extends AppCompatActivity {
    private EditText studentNameEditText, studentClassEditText, studentRollEditText, studentEmailEditText;
    private Button addStudentButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        databaseHelper = new DatabaseHelper(this);

        // Initialize EditText fields
        studentNameEditText = findViewById(R.id.StudentName);
        studentClassEditText = findViewById(R.id.Course);
        studentRollEditText = findViewById(R.id.studentRoll);

        addStudentButton = findViewById(R.id.addStudentBtn);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentNameEditText.getText().toString().trim();
                String rollNumber = studentRollEditText.getText().toString().trim();
                String course = studentClassEditText.getText().toString().trim();

                if (databaseHelper.addStudent(name, rollNumber, course)) {
                    Toast.makeText(addStudent.this, "Student added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(addStudent.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
