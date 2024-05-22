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

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void insertUser (View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }
    public void removeUser (View view) {
        // Handle the click event here
        // For example, show a toast message
        Toast.makeText(this, "Currently Not Available", Toast.LENGTH_SHORT).show();
    }
    public void Fees (View view) {
        // Handle the click event here
        // For example, show a toast message
        Toast.makeText(this, "Currently Not Available", Toast.LENGTH_SHORT).show();
    }
    public void addCourse (View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

}