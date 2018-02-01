package de.andreasschrade.androidtemplate.ui.quote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.andreasschrade.androidtemplate.R;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        TextView info = (TextView) findViewById(R.id.contacts);
        info.setText("Reach out to the Geek Girls" +"\n"+"    "+"\n" +
                "Head Office"+"\n" +
                "Mark Shuttleworth street, Enterprise Building,"+"\n" +
                "The Innovation Hub, Persequor, 0087"+"\n" +
                "Phone:012 844 0240"+"\n" +
                "Email Address:info@reaketsetsa.com"+"\n" +"Working Hours");


    }

}
