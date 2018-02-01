package de.andreasschrade.androidtemplate.ui.quote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.dummy.Event;
import de.andreasschrade.androidtemplate.ui.base.BaseActivity;
import de.andreasschrade.androidtemplate.ui.base.BaseFragment;

import static de.andreasschrade.androidtemplate.R.id.quote;


public class ArticleDetailFragment extends BaseFragment {

    /**
     * The argument represents the dummy item ID of this fragment.
     */
    public static final String ARG_ITEM_ID = "item_id";


    /**
     * The dummy content of this fragment.
     */
   // private DummyContent.DummyItem dummyItem;
    Event event;
    private Context context;


    @Bind(quote)
    TextView content;

    @Bind(R.id.author)
    TextView author;

    @Bind(R.id.backdrop)
    ImageView backdropImg;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // load event item by using the passed item ID.
            event= event.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));


        }




        setHasOptionsMenu(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflateAndBind(inflater, container, R.layout.fragment_article_detail);
        context = container.getContext();


        if (!((BaseActivity) getActivity()).providesActivityToolbar()) {
            // No Toolbar present. Set include_toolbar:
            ((BaseActivity) getActivity()).setToolbar((Toolbar) rootView.findViewById(R.id.toolbar));
        }

        if (event != null) {
            loadBackdrop();
           collapsingToolbar.setTitle(event.getTitle());
             content.setText(event.getContent());







        }

        return rootView;
    }

    private void loadBackdrop() {
        String img=event.getPhoto();
        Glide.with(context).load(img).into(backdropImg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sample_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        String shareText = "this";

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(intent, "Share"));

        return super.onOptionsItemSelected(item);
        }



    public static ArticleDetailFragment newInstance(String itemID) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        args.putString(ArticleDetailFragment.ARG_ITEM_ID, itemID);
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleDetailFragment() {}
}
