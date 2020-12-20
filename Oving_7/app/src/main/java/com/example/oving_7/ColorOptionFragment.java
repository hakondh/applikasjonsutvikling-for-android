package com.example.oving_7;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ColorOptionFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
