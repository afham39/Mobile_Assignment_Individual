package com.example.individualassigment_afham;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button githubButton = findViewById(R.id.button);
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/afham39/Mobile_Assignment_Individual"));
                startActivity(browserIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Go back to Home (MainActivity)
            Intent intent = new Intent(AboutActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.nav_about) {
            // We are already on About, do nothing
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}