package de.andreasschrade.androidtemplate.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import de.andreasschrade.androidtemplate.ui.quote.ProgrammesActivity;
import de.andreasschrade.androidtemplate.R;
import de.andreasschrade.androidtemplate.ui.quote.About;
import de.andreasschrade.androidtemplate.ui.quote.Contact;
import de.andreasschrade.androidtemplate.ui.quote.Faq;

import static de.andreasschrade.androidtemplate.util.LogUtil.logD;
import static de.andreasschrade.androidtemplate.util.LogUtil.makeLogTag;

/**
 * The base class for all Activity classes.
 * This class creates and provides the navigation drawer and toolbar.
 * The navigation logic is handled in {@link BaseActivity#goToNavDrawerItem(int)}
 *
 * Created by Andreas Schrade on 14.12.2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String TAG = makeLogTag(BaseActivity.class);
     Intent i;
    protected static final int NAV_DRAWER_ITEM_INVALID = -1;

    private DrawerLayout drawerLayout;
    private Toolbar actionBarToolbar;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    /**
     * Sets up the navigation drawer.
     */
    private void setupNavDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            // current activity does not have a drawer.
            return;
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerSelectListener(navigationView);
            setSelectedItem(navigationView);
        }

        logD(TAG, "navigation drawer setup finished");
    }

    /**
     * Updated the checked item in the navigation drawer
     * @param navigationView the navigation view
     */
    private void setSelectedItem(NavigationView navigationView) {
        // Which navigation item should be selected?
        int selectedItem = getSelfNavDrawerItem(); // subclass has to override this method
        navigationView.setCheckedItem(selectedItem);
    }

    /**
     * Creates the item click listener.
     * @param navigationView the navigation view
     */
    private void setupDrawerSelectListener(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        drawerLayout.closeDrawers();
                        onNavigationItemClicked(menuItem.getItemId());
                        return true;
                    }
                });
    }

    /**
     * Handles the navigation item click.
     * @param itemId the clicked item
     */
    private void onNavigationItemClicked(final int itemId) {
        if(itemId == getSelfNavDrawerItem()) {
            // Already selected
            closeDrawer();
            return;
        }

        goToNavDrawerItem(itemId);
    }

    /**
     * Handles the navigation item click and starts the corresponding activity.
     * @param item the selected navigation item
     */
    private void goToNavDrawerItem(int item) {
        NavigationView nv= (NavigationView) findViewById(R.id.nav_view);
        Menu m = nv.getMenu();
        switch (item) {
            case R.id.nav_about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                m.findItem(R.id.games).setVisible(false);
                m.findItem(R.id.digital).setVisible(false);
                m.findItem(R.id.robot).setVisible(false);
                m.findItem(R.id.business).setVisible(false);
                m.findItem(R.id.code).setVisible(false);


                break;

            case R.id.nav_quotes:
                Intent i = new Intent(this, Contact.class);
                startActivity(i);

                m.findItem(R.id.games).setVisible(false);
                m.findItem(R.id.digital).setVisible(false);
                m.findItem(R.id.robot).setVisible(false);
                m.findItem(R.id.business).setVisible(false);
                m.findItem(R.id.code).setVisible(false);
                break;

            case R.id.nav_samples:
                Intent intent1 = new Intent(this,Faq.class);
                startActivity(intent1);

                m.findItem(R.id.games).setVisible(false);
                m.findItem(R.id.digital).setVisible(false);
                m.findItem(R.id.robot).setVisible(false);
                m.findItem(R.id.business).setVisible(false);
                m.findItem(R.id.code).setVisible(false);
                break;
            case R.id.nav_programmes:

                Intent intent2 = new Intent(this, ProgrammesActivity.class);
                startActivity(intent2);


                break;







        }


    }




    /**
     * Provides the action bar instance.
     * @return the action bar.
     */
    protected ActionBar getActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (actionBarToolbar != null) {
                setSupportActionBar(actionBarToolbar);
            }
        }
        return getSupportActionBar();
    }


    /**
     * Returns the navigation drawer item that corresponds to this Activity. Subclasses
     * have to override this method.
     */
    protected int getSelfNavDrawerItem() {
        return NAV_DRAWER_ITEM_INVALID;
    }

    protected void openDrawer() {
        if(drawerLayout == null)
            return;

        drawerLayout.openDrawer(GravityCompat.START);
    }

    protected void closeDrawer() {
        if(drawerLayout == null)
            return;

        drawerLayout.closeDrawer(GravityCompat.END);
    }

    public abstract boolean providesActivityToolbar();

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




}
