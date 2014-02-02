package de.code4fun.ordernow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ItemFragment extends Fragment {

    String mCategoryId;

    public ItemFragment(String categoryId) {
        mCategoryId = categoryId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ItemAdapter adapter = new ItemAdapter(inflater.getContext(), mCategoryId);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        return rootView;
    }
}
