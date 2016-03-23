package projet.istic.fr.tp3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegionList extends Fragment {

    OnRegionSelectedListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("RegionList", "onCreate()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("RegionList", "onActivityCreated().");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");

        //Generate list View from ArrayList
        displayListView();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("RegionList", "onCreateView()");
        Log.v("ListContainer", container == null ? "true" : "false");
        Log.v("ListsavedInstanceState", savedInstanceState == null ? "true" : "false");
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.list_view, container, false);
        return view;
    }


    // Container Activity must implement this interface
    public interface OnRegionSelectedListener {
        public void onRegionSelected(String Region);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRegionSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnRegionSelectedListener");
        }
    }

    private void displayListView() {

        //Array list of countries
        List<String> regionList = new ArrayList<String>();
        regionList.add("Alsace");
        regionList.add("Beaujolais");
        regionList.add("Jura");
        regionList.add("Champagne");
        regionList.add("Savoie");
        regionList.add("Languedoc-Roussillon");
        regionList.add("Bordelais");
        regionList.add("Vallée du Rhone");
        regionList.add("Provence");
        regionList.add("Val de Loire");
        regionList.add("Sud-Ouest");
        regionList.add("Corse");
        regionList.add("Bourgogne");

        //create an ArrayAdaptar from the String Array
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.region_list, regionList);
        ListView listView = (ListView) getView().findViewById(R.id.listofRegions);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Send the URL to the host activity
                mListener.onRegionSelected(((TextView) view).getText().toString());

            }
        });

    }
}

