package com.example.birdpoe;import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToRegister = findViewById(R.id.button2);

        btnGoToRegister.setOnClickListener(v -> {
            // Start the RegisterActivity layout when the button is clicked
            setContentView(R.layout.activity_register);
        });
    }
}
