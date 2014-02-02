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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ViewGroup cont = container; //need to have this final
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ItemAdapter adapter = new ItemAdapter(inflater.getContext(), mCategoryId);

        adapter.addOnQueryLoadListener(new ParseQueryAdapter.OnQueryLoadListener<ParseObject>() {
            public void onLoading() {
                rootView.setVisibility(View.GONE);

                ProgressBar progrBar = (ProgressBar) cont.findViewById(R.id.progressCateg);
                progrBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoaded(List<ParseObject> parseObjects, Exception e) {
                rootView.setVisibility(View.GONE);

                ProgressBar progrBar = (ProgressBar) cont.findViewById(R.id.progressCateg);
                progrBar.setVisibility(View.GONE);
            }


        });


        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        return rootView;
    }
}
