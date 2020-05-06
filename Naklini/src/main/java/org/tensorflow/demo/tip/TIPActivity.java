package org.tensorflow.demo.tip;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import org.tensorflow.demo.DetectorActivity;
import org.tensorflow.demo.R;
import org.tensorflow.demo.map.map;
import org.tensorflow.demo.nofish.activity.MainActivity;
import org.tensorflow.demo.nofish.activity.NoFishData;
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

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tip);
            getSupportActionBar().setIcon(R.drawable.mainicon);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        drawerLayout = findViewById(R.id.main_drawer);
        //액션바에 버튼 설정 - 버튼을 선택하면 NavigationView가 display
        //                    버튼을 다시 선택하면 NavigationView가 화면에서 사라지도록 설정
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout, R.string.open_str,R.string.close_str);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.Item0){
                    Intent intent = new Intent(TIPActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.Item1){
                    Intent intent = new Intent(TIPActivity.this, map.class);
                    startActivity(intent);
                }
                if (id == R.id.Item2){
                    Intent intent = new Intent(TIPActivity.this, DetectorActivity.class);
                    startActivity(intent);
                }
                if (id == R.id.Item3){
                    Intent intent = new Intent(TIPActivity.this, NoFishData.class);
                    startActivity(intent);
                }
                if (id == R.id.Item4){
                    Intent intent = new Intent(TIPActivity.this, TIPActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){

        }
        return super.onOptionsItemSelected(item);
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
        if(v.getId()== R.id.youtubebar){
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

