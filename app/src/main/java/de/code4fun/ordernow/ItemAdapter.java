package de.code4fun.ordernow;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.text.DecimalFormat;

/**
 */
public class ItemAdapter extends ParseQueryAdapter<ParseObject> {
    final DecimalFormat fmt = new DecimalFormat( "#,###,###,##0.00" );
    String mCategoryId;

    public ItemAdapter(Context context, final String categoryId) {
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                ParseQuery query = new ParseQuery("Item");
                query.whereEqualTo("category_id", categoryId);
                query.orderByAscending("name");

                query.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
                query.setMaxCacheAge(3600 * 1000);

                return query;
            }
        });

        setAutoload(true);
    }

    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.item_layout, null);
        }

        TextView titleTextView = (TextView) v.findViewById(R.id.title);
        TextView priceTextView = (TextView) v.findViewById(R.id.price);
        ParseImageView imageView = (ParseImageView) v.findViewById(R.id.image);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.description);

        titleTextView.setText(object.getString("name"));
        Number number = object.getNumber("price");
        if (number != null) {
            priceTextView.setText("$" + fmt.format(number.doubleValue() / 100));
        } else {
            priceTextView.setText("");
        }
        imageView.setParseFile(object.getParseFile("image"));
        imageView.loadInBackground();
        descriptionTextView.setText(object.getString("description"));

        return v;
    }
}
