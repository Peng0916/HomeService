package com.android.home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.home.R;
import com.android.home.util.GridItem;
import com.android.home.util.Viewholder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PengYue on 2016/6/17.
 * gridview适配器
 */
public class GridItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<GridItem> gridItemList;

    public GridItemAdapter(String[] title, int[] magerId, String[] description, Context context) {
        super();
        gridItemList = new ArrayList<GridItem>();
        inflater = LayoutInflater.from(context);
        for (int i = 0; i < magerId.length; i++) {
            GridItem item = new GridItem(title[i], magerId[i], description[i]);
            gridItemList.add(item);
        }
    }

    @Override
    public int getCount() {
        if (null != gridItemList) {
            return gridItemList.size();
        } else {

            return 0;
        }
    }

    @Override
    public Object getItem(int position) {

        return gridItemList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.grid_item,null);
            viewholder.title = convertView.findViewById(R.id.tv_grid_view_title);
        }

        return null;
    }
}
