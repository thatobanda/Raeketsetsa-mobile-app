package de.andreasschrade.androidtemplate.ui.quote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.dummy.DummyContent;
import de.andreasschrade.androidtemplate.dummy.Event;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;
import de.andreasschrade.androidtemplate.util.LogUtil;

import static de.andreasschrade.androidtemplate.ui.quote.ArticleListFragment.Callback;


public class ListActivity extends BaseActivity implements Callback {

    private boolean twoPaneMode;
    //ListView lvMain;
    private RecyclerView recyclerView;
    private DatabaseReference db;
    private EventAdapter mAdapter;
    private ArrayList<Event> arry = new ArrayList<>();
    Context context;
    private Event objectEvent;
    private ImageView imageView;
    private TextView content;

    Event event;
    public static final String CONTENT = "content";
    public static final String PHOTO = "photo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        context = getBaseContext();
        setupToolbar();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setLogo(R.mipmap.toolbar_icon);


        if (isTwoPaneLayoutUsed()) {
            twoPaneMode = true;
            LogUtil.logD("TEST", "TWO POANE TASDFES");
            enableActiveItemState();
        }

        if (savedInstanceState == null && twoPaneMode) {
            setupDetailFragment();
        }



        imageView = (ImageView) findViewById(R.id.thumbnail);

        content = (TextView) findViewById(R.id.quote);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //Listen to the events path on firebase
        DatabaseReference myRef = database.getReference("Event");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    //   recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
      //  ContextCompat.getDrawable(context, R.drawable.line_divider);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Loop through the event list while adding them on the eventList (array list of type event object)
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.i(TAG, snapshot.toString());
                    Event d = snapshot.getValue(Event.class);

                    arry.add(d);

                    //create an adapter
                    mAdapter = new EventAdapter(arry);
                    // add adapter to the recylerview
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Event e = arry.get(position);


                String desc = e.getContent().toString();
                String desc2 = e.getContent2().toString();
                String url = e.getPhoto();
                String name = e.getAuthor();


                Intent intent = new Intent(ListActivity.this, InfoActivity.class);
                intent.putExtra("content", desc);
                intent.putExtra("content2", desc2);
                intent.putExtra("name", name);
                intent.putExtra("imageUrl", url);


                startActivity(intent);


            }


            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


    /**
     * Called when an item has been selected
     *
     * @param id the selected quote ID
     */
    @Override
    public void onItemSelected(String id) {
        if (twoPaneMode) {
            // Show the quote detail information by replacing the DetailFragment via transaction.
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(id);
            getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
        } else {
            // Start the detail activity in single pane mode.
            Intent detailIntent = new Intent(this, ArticleDetailActivity.class);
            detailIntent.putExtra(ArticleDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    private void setupToolbar() {
        final ActionBar ab = getActionBarToolbar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupDetailFragment() {
        ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(DummyContent.ITEMS.get(0).id);
        getFragmentManager().beginTransaction().replace(R.id.article_detail_container, fragment).commit();
    }

    /**
     * Enables the functionality that selected items are automatically highlighted.
     */
    private void enableActiveItemState() {
        ArticleListFragment fragmentById = (ArticleListFragment) getFragmentManager().findFragmentById(R.id.article_list);
        fragmentById.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /**
     * Is the container present? If so, we are using the two-pane layout.
     *
     * @return true if the two pane layout is used.
     */
    private boolean isTwoPaneLayoutUsed() {
        return findViewById(R.id.article_detail_container) != null;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        openDrawer();
        return true;
    }


    @Override
    public boolean providesActivityToolbar() {
        return true;
    }
}
