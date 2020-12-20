package com.example.oving_4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Picture> pictures;
    private Context context;
    private ListFragment fragment;
    private int currentPos = 0;

    public CustomAdapter(ArrayList<Picture> pictures, Context context, ListFragment fragment) {
        this.pictures = pictures;
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int pos) {
        return pictures.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
            Log.i("view-check", "view is null");
        }

        // Get title, id and info for the image
        final Picture pic = pictures.get(position);
        final String titleString = pic.getTitle();

        // Adding the title of the picture in the specific list element
        TextView textView = view.findViewById(R.id.title);
        textView.setText(titleString);

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("click", "List element clicked");
                currentPos = pictures.indexOf(pic);
                fragment.changeCurrentImage(pic);
            }
        });
        return view;
    }

    public int getCurrentPos(){
        return  currentPos;
    }

    public void setCurrentPos(int pos){
        this.currentPos = pos;
    }

}