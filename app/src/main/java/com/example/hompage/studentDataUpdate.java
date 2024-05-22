package com.example.hompage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class studentDataUpdate extends AppCompatActivity {
    private EditText newNameEditText, newCourseEditText, rollNumberEditText;
    private Button updateButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data_update);

        databaseHelper = new DatabaseHelper(this);

        newNameEditText = findViewById(R.id.newName);
        newCourseEditText = findViewById(R.id.newCourse);
        rollNumberEditText = findViewById(R.id.studentRollNumber);
        updateButton = findViewById(R.id.btnUpdate);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNumber = rollNumberEditText.getText().toString().trim();
                String newName = newNameEditText.getText().toString().trim();
                String newCourse = newCourseEditText.getText().toString().trim();

                if (databaseHelper.updateStudent(rollNumber, newName, newCourse)) {
                    Toast.makeText(studentDataUpdate.this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(studentDataUpdate.this, "Failed to update student", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
