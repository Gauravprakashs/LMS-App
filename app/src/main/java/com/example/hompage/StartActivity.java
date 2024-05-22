package com.example.hompage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hompage.DatabaseHelper;

public class StartActivity extends AppCompatActivity {
    private Spinner spinnerType;
    private String[] userTypes = {"Teacher", "Student", "Admin"};
    private EditText usernameEditText, passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Spinner
        spinnerType = findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);


        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize EditText fields
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Button loginButton = findViewById(R.id.loginButton);
      //  Button registerButton = findViewById(R.id.registerButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String userType = spinnerType.getSelectedItem().toString();

                if (databaseHelper.checkUser(username, password, userType)) {
                    // User exists, proceed to dashboard or another activity
                    if (userType.equals("Teacher")) {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else if (userType.equals("Student")) {
                        Intent intent = new Intent(StartActivity.this, StudentDashboard_Activity.class);
                        startActivity(intent);
                    } else if (userType.equals("Admin")) {
                        Intent intent = new Intent(StartActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }
                    finish(); // Optional: Finish this activity to prevent going back
                } else {
                    // User doesn't exist or credentials are incorrect
                    Toast.makeText(StartActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });





      /*  registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open RegisterUserActivity when the register button is clicked
                Intent intent = new Intent(StartActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
