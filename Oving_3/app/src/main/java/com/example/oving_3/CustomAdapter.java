package com.example.oving_3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<Friend> friends;
    private Context context;
    static final int request_Code = 2;

    public CustomAdapter(ArrayList<Friend> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public int getCount() {
        return friends.size();
    }

    @Override
    public Object getItem(int pos) {
        return friends.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0; // Friend has no id variable

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
            Log.i("view-check", "view is null");
        }

        // Set the name and birth date in the TextView
        TextView listItemText = (TextView)view.findViewById(R.id.listItemString);
        final String name = friends.get(position).getName();
        final String birtdate = friends.get(position).getBirthdate();
        listItemText.setText(name + ", født " + birtdate);

        // Adding event listener to the edit button
        Button editBtn = (Button)view.findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("context", context.toString());
                Intent intent = new Intent(context, ChangeActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("name", name);
                intent.putExtra("birthdate", birtdate);
                ((Activity) context).startActivityForResult(intent, request_Code); // Start activity for result in the context of MainActivity
            }
        });
        return view;
    }
}