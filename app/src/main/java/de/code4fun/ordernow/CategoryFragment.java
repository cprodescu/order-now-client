package de.code4fun.ordernow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CategoryFragment extends Fragment {
    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ParseQueryAdapter.QueryFactory<ParseObject> factory =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery create() {
                        ParseQuery query = new ParseQuery("Category");

                        // TODO select on user props

                        return query;
                    }
                };

        // Pass the factory into the ParseQueryAdapter's constructor.
        ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(getActivity().getApplicationContext(), factory);
        adapter.setTextKey("name");
        adapter.setImageKey("image");

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        /*
        // Perhaps set a callback to be fired upon successful loading of a new set of ParseObjects.
        adapter.addOnQueryLoadListener(new OnQueryLoadListener<ParseObject>() {
            public void onLoading() {
                // Trigger any "loading" UI
            }

            public void onLoaded(List<ParseObject> objects, ParseException e) {
                // Execute any post-loading logic, hide "loading" UI
            }
        });

        // Attach it to your ListView, as in the example above
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        */
        return rootView;
    }
}
