package de.andreasschrade.androidtemplate.ui.quote;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.dummy.Event;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;


public class ArticleDetailActivity extends BaseActivity {

    TextView title,content;
    private Event e;
    private List<Event> mpArray;

    public static final String CONTENT = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /**TextView qoute = (TextView)findViewById(R.id.quote);

       Intent i = getIntent();
       String content = i.getStringExtra("content");
        qoute.setText(content);
//       qoute.setText(content); */



        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ArticleDetailFragment fragment =  ArticleDetailFragment.newInstance(getIntent().
                getStringExtra(ArticleDetailFragment.ARG_ITEM_ID));
        getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();



    }



    @Override
    public boolean providesActivityToolbar() {
        return false;
    }
}
