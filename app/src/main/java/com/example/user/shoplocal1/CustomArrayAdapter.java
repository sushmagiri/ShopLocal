package com.example.user.shoplocal1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomArrayAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<Entity> entityArrayList;
    Context context;

    public CustomArrayAdapter(Context contextList, ArrayList<Entity> list) {
        this.entityArrayList = list;
        layoutInflater = LayoutInflater.from(contextList);
        this.context = contextList;
    }

    @Override
    public int getCount() {
        return entityArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return entityArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.name_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.price);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);

        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.name.setText(entityArrayList.get(position).getName());
        viewHolder.price.setText(entityArrayList.get(position).getPrice());
        Picasso.with(context).load(entityArrayList.get(position).getImage()).into(viewHolder.imageView);

        return convertView;
    }

    public static class ViewHolder {
        TextView name;
        TextView price;
        ImageView imageView;
    }
}



