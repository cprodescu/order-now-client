package de.code4fun.ordernow;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQueryAdapter.OnQueryLoadListener;
import com.parse.ParseException;

import java.util.List;

public class CategoryFragment extends Fragment {
    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ParseQueryAdapter.QueryFactory<ParseObject> factory =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery create() {
                        ParseQuery query = new ParseQuery("Category");

                        // TODO select on user props
                        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
                        query.setMaxCacheAge(3600 * 1000);
                        return query;
                    }
                };

        // Pass the factory into the ParseQueryAdapter's constructor.
        final ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(getActivity().getApplicationContext(), factory);
        adapter.setTextKey("name");
        adapter.setImageKey("image");

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParseObject item = adapter.getItem(position);
                String categoryId = item.getString("category_id");

                getActivity().getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new ItemFragment(categoryId))
                        .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK)
                        .addToBackStack(null)
                        .commit();
            }
        });
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
