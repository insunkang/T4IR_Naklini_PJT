package org.tensorflow.demo.tip.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.tensorflow.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Link extends Fragment {

    public Link() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.link, container, false);
    }
}
