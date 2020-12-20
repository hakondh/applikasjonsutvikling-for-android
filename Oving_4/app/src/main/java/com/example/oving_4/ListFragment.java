package com.example.oving_4;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;


public class ListFragment extends Fragment {
    private ArrayList<Picture> pictures;
    private CustomAdapter adapter;
    private Resources res;

    public ListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        res = getResources();

        pictures = new ArrayList<>();
        //Adding new example-pictures to the list
        pictures.add(new Picture("Mount Everest", ResourcesCompat.getDrawable(res, R.drawable.mount_everest, null), "Mount Everest er verdens høyeste fjell. Mount Everest ligger i fjellkjeden Mahalangur Himal i Himalaya, på grensen mellom Folkerepublikken Kina og Nepal, og grensen går midt over fjellets toppunkt."));
        pictures.add(new Picture("K2", ResourcesCompat.getDrawable(res, R.drawable.k2, null), "K2 er verdens nest høyeste fjell. Det ligger i Kashmir, i fjellkjeden Karakorum i Gilgit-Baltistan på grensen mellom Pakistan og Folkerepublikken Kina."));
        pictures.add(new Picture("Kangchenjunga", ResourcesCompat.getDrawable(res, R.drawable.kangchenjunga, null), "Kangchenjunga er verdens tredje høyeste fjell. Fjellet ligger på grensen mellom den tidligere selvstendige staten Sikkim (nå delstat i India) og det østlige Nepal"));

        ListView pictureList = (ListView)view.findViewById(R.id.pictureList);
        adapter = new CustomAdapter(pictures, getContext(), ListFragment.this);
        pictureList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        changeCurrentImage(pictures.get(0)); // Need to call this at creation to get the title and description.
    }


    // Method to change the picture when element in list is selected.
    public void changeCurrentImage(Picture picture){
        FragmentManager fm = getFragmentManager();
        PictureFragment pf = (PictureFragment) fm.findFragmentById(R.id.fragPic);
        pf.changeImage(picture);
    }

    public void navigateImages(int direction){
        int currentPosition = adapter.getCurrentPos(); // Position of the picture currently selected
        int size = adapter.getCount(); // Need the size to check if index can be incremented or not

        // Check if we should change to next or previous picture in the list
        // Also checks if the next/previous image exist or not. If not, go back to first or last image.
        if (direction > 0){
            int nextIndex = currentPosition + 1;
            Picture newPicture;


            if(nextIndex == size){
                newPicture = pictures.get(0);
                adapter.setCurrentPos(0);
            }
            else {
                newPicture = pictures.get(nextIndex);
                adapter.setCurrentPos(nextIndex);
            }
            changeCurrentImage(newPicture);
        }
        else {
            int previousIndex = currentPosition - 1;
            Picture newPicture;

            if(previousIndex < 0){
                int lastIndex = pictures.size() - 1;
                newPicture = pictures.get(lastIndex); // We get the last image of the list
                adapter.setCurrentPos(lastIndex);
            }
            else {
                newPicture = pictures.get(previousIndex); // We get the previous image in the list
                adapter.setCurrentPos(previousIndex);
            }
            changeCurrentImage(newPicture);
        }

    }
}
