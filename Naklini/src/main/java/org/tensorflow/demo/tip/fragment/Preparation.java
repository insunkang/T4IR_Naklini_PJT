package org.tensorflow.demo.tip.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.tensorflow.demo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Preparation extends Fragment implements YouTubePlayer.OnInitializedListener{
    private static final String API_KEY = "AIzaSyAYY81JoGkdoDUcAu8v-GYOJx0Bz3yIne8";
    private static String VIDEO_ID="crpZtQTynnY";
    View v;
    public Preparation() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(v==null){
            v = inflater.inflate(R.layout.preparation,container,false);
            // Inflate the layout for this fragment
            YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment,youTubePlayerFragment).commit();
            youTubePlayerFragment.initialize(API_KEY, this);
        }
        else{
            YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment,youTubePlayerFragment).commit();
            youTubePlayerFragment.initialize(API_KEY, this);
        }
        return v;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
        if(!b){
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
            player.cueVideo(VIDEO_ID);
            player.play();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
