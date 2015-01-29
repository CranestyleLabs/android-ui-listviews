package com.cranestylelabs.danhoffman.listviewsample.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cranestylelabs.danhoffman.listviewsample.models.ListItemData;
import com.cranestylelabs.danhoffman.listviewsample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FilterAdapter extends DownloadDataAdapter implements Filterable {

    private List<ListItemData> mDataList = new ArrayList<ListItemData>();
    private List<ListItemData> mDataListMutable = new ArrayList<ListItemData>();
    private Context mContext;

    public FilterAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public FilterAdapter(Context context, List<String> names) {
        super();
        this.mContext = context;
        updateDataList(names);
    }

    public void updateDataList(List<String> names) {
        mDataList = this.getDataForListView(names);
        mDataListMutable = mDataList;
    }

    @Override
    public int getCount() {
        return mDataListMutable.size();
    }

    @Override
    public ListItemData getItem(int position) {
        return mDataListMutable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_list_item, parent, false);
        }
        TextView title = (TextView)convertView.findViewById(R.id.textView1);
        TextView desc = (TextView)convertView.findViewById(R.id.textView2);

        ListItemData obj = mDataListMutable.get(position);

        title.setText(obj.title);
        desc.setText(obj.description);

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                Log.d("", "**** PUBLISHING RESULTS for: " + constraint);
                mDataListMutable = (List<ListItemData>) results.values;
                FilterAdapter.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Log.d("", "**** PERFORM FILTERING for: " + constraint);
                List<ListItemData> filteredResults = getFilteredResults(constraint);

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }
        };
    }

    public List<ListItemData> getDataForListView(List<String> names) {
        List<ListItemData> listItemDataList = new ArrayList<ListItemData>();
        int i = 0;
        for(String name: names) {
            ListItemData obj = new ListItemData();
            obj.title = name;
            obj.description = "Description for " + i++;
            listItemDataList.add(obj);
        }
        return listItemDataList;
    }


    public List<ListItemData> getFilteredResults(CharSequence constraint) {
        List<ListItemData> results = new ArrayList<ListItemData>();

        for (Iterator<ListItemData> iter = this.mDataList.iterator(); iter.hasNext(); ) {
            ListItemData item = iter.next();
            if (item.title.toLowerCase().contains(constraint.toString().toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    @Override
    public void returnedData(String data) {
        List<String> serverData = new ArrayList<String>();
        try {
            JSONArray jsonArray = this.processJson(data, "data");
            serverData = this.jsonToStringArray(jsonArray);
            this.updateDataList(serverData);
            this.notifyDataSetChanged();
        } catch (JSONException e) {
            //TODO: update UI to notify user
            Log.e("", "Missing or malformed JSON string", e);
        }
    }

    private JSONArray processJson(String jsonString, String arrayKey) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONArray(arrayKey);
    }

    private List<String> jsonToStringArray(JSONArray jsonArray) throws JSONException {
        List<String> stringList = new ArrayList<String>();
        for (int i=0; i<jsonArray.length(); i++) {
            stringList.add(jsonArray.getString(i));
        }
        return stringList;
    }
}
