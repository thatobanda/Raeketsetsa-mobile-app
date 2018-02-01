package de.andreasschrade.androidtemplate.ui.quote;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.andreasschrade.androidtemplate.R;

public class ProgrammesActivity extends AppCompatActivity {
    final ArrayList<String> services = new ArrayList<>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmes);


        ListView lv = (ListView) findViewById(R.id.lv);



        services.add("Mobile Dev Club");
        services.add("Graphics,Animation and Gaming Club");
        services.add("Coding Club");
        services.add("Robot Programing Club");
        services.add("Digital Skills Club");
        services.add("Business Enterprise");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,services){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(20);


                // Generate ListView Item using TextView
                return view;
            }
        };

        lv.setAdapter(arrayAdapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=services.get(position);

                if(name=="Mobile Dev Club")
                {
                    AlertDialog();
                }
                else if(name=="Graphics,Animation and Gaming Club")
                {
                    AlertDialog2();
                }
                else if(name=="Coding Club")
                {
                    AlertDialog3();
                }
                else if(name=="Robot Programing Club")
                {
                    AlertDialog4();
                }
                else if(name=="Digital Skills Club")
                {
                    AlertDialog5();
                }
                else if(name=="Business Enterprise")
                {
                    AlertDialog6();
                }
            }
        });


    }
    public  void AlertDialog()
    {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AlertDialog);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
        builder.setTitle("Mobile Dev Club");
        builder.setMessage("Building a mobile APP will give young women a glimpse of the" +
                " booming app industry,and let them experience");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    public  void AlertDialog2()
    {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AlertDialog);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
        builder.setTitle("Graphics,Animation and Gaming Club");
        builder.setMessage("Young Women will create animations,simple educational games and" +
                " videos to share on the website");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
    public  void AlertDialog3()
    {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AlertDialog);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
        builder.setTitle("Coding Club");
        builder.setMessage("Coding is at the heart of today's digital world.Hence,it is " +
                "essential that young women are introduced to the logic ");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    public  void AlertDialog4()
    {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AlertDialog);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
        builder.setTitle("Robot Programing Club");
        builder.setMessage("By introducing robotics to women will give them a chance to experience the future," +
                "girls and young women can learn the basics of robotics with" +
                " LEGO MINDSTORM EV3 Robots and experiment ");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    public  void AlertDialog5()
    {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AlertDialog);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
        builder.setTitle("Digital Skills Club");
        builder.setMessage("Young women with less experience with computers will acquire basic computer skills." +
                "Learning how to switch on the computer,how to open their own email" +
                " accounts,and introductory on Microsoft Word ");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
    public  void AlertDialog6()
    {
        ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.Theme_AlertDialog);

        android.support.v7.app.AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
        builder.setTitle("Business Enterprise");
        builder.setMessage("Teach about how to build a business model.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

}

