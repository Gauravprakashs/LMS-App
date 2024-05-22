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

public class RegisterUserActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        databaseHelper = new DatabaseHelper(this);

        EditText usernameEditText = findViewById(R.id.registerUsernameEditText);
        EditText passwordEditText = findViewById(R.id.registerPasswordEditText);
        Spinner spinner = findViewById(R.id.spinnerRegisteredType);
        Button registerButton = findViewById(R.id.registerButton);

        String[] options = {"Student", "Teacher"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String userType = spinner.getSelectedItem().toString();

                long id = databaseHelper.addUser(username, password, userType);
                if (id != -1) {
                    // Registration successful, show a toast
                    Toast.makeText(RegisterUserActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    // Optionally, navigate to another activity after successful registration
                    Intent intent = new Intent(RegisterUserActivity.this, StartActivity.class);
                    startActivity(intent);
                    finish(); // Finish this activity to prevent going back
                } else {
                    // Registration failed, show an error message
                    Toast.makeText(RegisterUserActivity.this, "Error Registering User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
