package com.example.oving_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class PictureFragment extends Fragment {
    private View view;

    public PictureFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         view = inflater.inflate(R.layout.fragment_picture, container, false);

        return view;
    }

    // Method for changing image when an item is selected in the list
    public void changeImage(Picture pic){
        TextView tvTitle = view.findViewById(R.id.textView);
        tvTitle.setText(pic.getTitle());
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageDrawable(pic.getResource());

        TextView textView = view.findViewById(R.id.info);
        textView.setText(pic.getInfo());
    }
}
