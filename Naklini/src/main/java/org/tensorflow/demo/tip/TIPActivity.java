package org.tensorflow.demo.tip;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.tensorflow.demo.R;
import org.tensorflow.demo.tip.fragment.Bait;
import org.tensorflow.demo.tip.fragment.Link;
import org.tensorflow.demo.tip.fragment.Preparation;
import org.tensorflow.demo.tip.fragment.Rod;
import org.tensorflow.demo.tip.fragment.Sort;

public class TIPActivity extends AppCompatActivity{
    Button rod_btn;
    Button pre_btn;
    Button bait_btn;
    Button sort_btn;
    Rod rod;
    Preparation preparation;
    Bait bait;
    Sort sort;
    Link link;
    @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tip);
            rod_btn=findViewById(R.id.rod_btn);
            pre_btn=findViewById(R.id.pre_btn);
            bait_btn=findViewById(R.id.bait_btn);
            sort_btn=findViewById(R.id.sort_btn);
            rod_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFragment("rod");
                    }
                });
            pre_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFragment("pre");
                    }
            });
            bait_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFragment("bait");
                    }
            });
            sort_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFragment("sort");
                    }
            });



    }

    public void setFragment(String name){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (name){
            case "rod":
                rod = new Rod();
                transaction.replace(R.id.frameframe,rod);
                break;
            case "pre":
                preparation = new Preparation();
                transaction.replace(R.id.frameframe,preparation);
                break;
            case "bait":
                bait = new Bait();
                transaction.replace(R.id.frameframe,bait);
                break;
            case "sort":
                sort = new Sort();
                transaction.replace(R.id.frameframe,sort);
        }
        transaction.commit();
    }
}

