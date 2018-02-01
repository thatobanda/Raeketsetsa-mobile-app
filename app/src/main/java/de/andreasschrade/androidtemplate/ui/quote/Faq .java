package de.andreasschrade.androidtemplate.ui.quote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.andreasschrade.androidtemplate.R;

public class Faq extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("How do I check their events?");
        listDataHeader.add("What are the benefits of joining the programme?");
        listDataHeader.add("Who can participate?");
        listDataHeader.add("Who is in charge of the programme?");

        // Adding child data
        List<String> check= new ArrayList<String>();
       check.add("Please frequently check their website  and social media pages for upcoming events");


        List<String> benefits = new ArrayList<String>();
        benefits.add("young women will see that they dont need to be" +
                " math wizads inorder to code to be programmers.we empower young girls to do " +
                "the following:\n" +
                "Electronics\n" +
                "Mobile App development\n" +
                "Web development\n" +
                "Business Analysis");



        List<String> participate = new ArrayList<String>();
        participate.add("Any young woman who has interest in the ICT field,who needs empowerment" +
                " motivation and wants to gain the necessary skills in the ICT field while realizing" +
                " entrepreneurship oppurtunities.");


        List<String> incharge = new ArrayList<String>();
        incharge.add("Sinah Legong is the founder of this programmes");
        listDataChild.put(listDataHeader.get(0), check); // Header, Child data
        listDataChild.put(listDataHeader.get(1), benefits);
        listDataChild.put(listDataHeader.get(2),participate);
        listDataChild.put(listDataHeader.get(3), incharge);
    }
}
