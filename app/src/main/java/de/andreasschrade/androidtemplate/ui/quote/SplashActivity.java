package de.andreasschrade.androidtemplate.ui.quote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by geekulcha on 11/10/2017.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}
