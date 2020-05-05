package org.tensorflow.demo.tip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.tensorflow.demo.R;
import org.tensorflow.demo.tip.fragment.Bait;
import org.tensorflow.demo.tip.fragment.Preparation;
import org.tensorflow.demo.tip.fragment.Rod;
import org.tensorflow.demo.tip.fragment.Sort;

public class TIPActivity extends AppCompatActivity implements View.OnClickListener {
    Button rod_btn;
    Button pre_btn;
    Button bait_btn;
    Button sort_btn;
    //ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    //ViewPager viewpager;
    Rod rod = new Rod();
    Preparation preparation = new Preparation();
    Bait bait = new Bait();
    Sort sort = new Sort();

    @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tip);
            /*viewpager=findViewById(R.id.viewpager);
            fragmentArrayList.add(rod);
            fragmentArrayList.add(preparation);
            fragmentArrayList.add(bait);
            fragmentArrayList.add(sort);
            fragmentArrayList.add(link);
            FragAdapter adapter = new FragAdapter(getSupportFragmentManager(),fragmentArrayList.size());
            viewpager.setAdapter(adapter);*/

            rod_btn=findViewById(R.id.rod_btn);
            pre_btn=findViewById(R.id.pre_btn);
            bait_btn=findViewById(R.id.bait_btn);
            sort_btn=findViewById(R.id.sort_btn);
            findViewById(R.id.youtubebar).setOnClickListener(this);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            rod_btn.setOnClickListener(new View.OnClickListener() {
                int count=0;
                @Override
                public void onClick(View v) {
                    if(count%2==0){
                        setFragment("rod");
                        count++;
                    }
                    else if(count%2==1){
                        removeFragment("rod");
                        count++;
                    }
                }
            });

            pre_btn.setOnClickListener(new View.OnClickListener() {
                int count=0;
                @Override
                public void onClick(View v) {
                    if(count%2==0){
                        setFragment("pre");
                        count++;
                    }
                    else if(count%2==1){
                        removeFragment("pre");
                        count++;
                    }
                }
            });
            bait_btn.setOnClickListener(new View.OnClickListener() {
                int count=0;
                @Override
                public void onClick(View v) {
                    if(count%2==0){
                        setFragment("bait");
                        count++;
                    }
                    else if(count%2==1){
                        removeFragment("bait");
                        count++;
                    }
                }
            });
            sort_btn.setOnClickListener(new View.OnClickListener() {
                int count=0;
                @Override
                public void onClick(View v) {
                    if(count%2==0){
                        setFragment("sort");
                        count++;
                    }
                    else if(count%2==1){
                        removeFragment("sort");
                        count++;
                    }
                }
            });



    }

   public void setFragment(String name){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (name){
            case "rod":
                transaction.replace(R.id.frameframe,rod);
                break;
           case "pre":
               transaction.replace(R.id.frameframe,preparation);
               break;

            case "bait":
                transaction.replace(R.id.frameframe,bait);
                break;
            case "sort":
                transaction.replace(R.id.frameframe,sort);
        }
        transaction.commitAllowingStateLoss();
    }
    public void removeFragment(String name){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (name){
            case "rod":
                transaction.remove(rod);
                break;
            case "pre":
                transaction.remove(preparation);
                break;
            case "bait":
                transaction.remove(bait);
                break;
            case "sort":
                transaction.remove(sort);
        }
        transaction.commitAllowingStateLoss();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.youtubebar){
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    /*class FragAdapter extends FragmentStatePagerAdapter{

        public FragAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }
    }
    public void btn_click(View v){
        viewpager.setCurrentItem(Integer.parseInt(viewpager.getTag().toString()));
    }*/
}

