package de.andreasschrade.androidtemplate.ui.quote;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.dummy.Event;

public class InfoActivity extends AppCompatActivity {
    private Event event;
    private ImageView imageView;
    Context context;
    TextView qoute, author, qoute2;
    ArrayList<Event> arrayList = new ArrayList<Event>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        toolbar  = (Toolbar) findViewById(R.id.toolbar);
        qoute = (TextView) findViewById(R.id.quote);
        qoute2 = (TextView) findViewById(R.id.quote2);
        imageView = (ImageView) findViewById(R.id.backdrop);

        author = (TextView) findViewById(R.id.textView3);

        Intent i = getIntent();
        String content = i.getStringExtra("content");
        String content2 = i.getStringExtra("content2");
        String name = i.getStringExtra("name");

        toolbar.setTitle(name);
        setSupportActionBar(toolbar);

        String url = getIntent().getExtras().getString("imageUrl");
        Glide.with(InfoActivity.this).load(url).into(imageView);
        author.setText("More Info");

        qoute.setText(content);
        qoute2.setText(content2);


        FloatingActionButton share = (FloatingActionButton) findViewById(R.id.button_share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String appUrl = "https://android-design-template-master.firebaseio.com/Event";

                Intent intent2 = new Intent();

                intent.setAction(Intent.ACTION_SEND);

                String url = getIntent().getExtras().getString("imageUrl");

                Intent i = getIntent();

                Uri uri = Uri
                        .parse(url);

                String name = i.getStringExtra("name");


                String content = i.getStringExtra("content");


                String body = name + "\n" + "\n" + content + "\n" + "\n" + appUrl;

                intent.setType("text/plain");

                intent2.setType("image/jpg");


                intent.putExtra(Intent.EXTRA_TEXT, body);

                intent2.putExtra(Intent.EXTRA_STREAM, uri);


                startActivity(Intent.createChooser(intent, "Share via"));


            }
        });


        /**final Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.bounce);
         share.startAnimation(myAnim);




         share.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {

        Intent intent = new Intent();

        Intent intent2 = new Intent();

        intent.setAction(Intent.ACTION_SEND);

        String url = getIntent().getExtras().getString("imageUrl");

        Intent i = getIntent();

        Uri uri = Uri
        .parse(url);

        String name=i.getStringExtra("name");


        String content = i.getStringExtra("content");


        String body=name+  "\n" + "\n"+content;

        intent.setType("text/plain");

        intent2.setType("image/jpg");


        intent.putExtra(Intent.EXTRA_TEXT,body);
        intent2.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(intent, "Share via"));



        }
        });*/
    }




}

