package de.code4fun.ordernow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.List;

public class ItemFragment extends Fragment {

    String mCategoryId;

    public ItemFragment(String categoryId) {
        mCategoryId = categoryId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        /*
        adapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<ParseObject>() {
            @Override
            public void onLoading() {
                ProgressBar progrBar = (ProgressBar) container.findViewById(R.id.progressCateg);
                progrBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoaded(List<ParseObject> parseObjects, Exception e) {
                ProgressBar progrBar = (ProgressBar) container.findViewById(R.id.progressCateg);
                progrBar.setVisibility(View.GONE);
            }
        });
        */

        ItemAdapter adapter = new ItemAdapter(inflater.getContext(), mCategoryId);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        return rootView;
    }
}
